# Verify Concor Contribution Records

Connects to the MAAT DB Schema and verifies that the XML in the
`CONCOR_CONTRIBUTIONS` table can be loaded and parsed using specific
DTO classes and that deserializing the original XML content into the
DTO, then re-serializing the DTO back into XML has comparable content.

Additional checks include serializing each DTO as JSON, and verifying
that the output JSON matches the JSON schema.

For each `CONCOR_CONTRIBUTIONS.FULL_XML` value in the database,
perform the following checks (and iterate using some form of paging
so memory usage remains constrained):
- Transform XML -> DTO
- Transform DTO -> JSON
- Validate the transformed JSON
- Transform JSON -> DTO
- Transform DTO -> XML
- Compare the transformed XML to the original XML

During this process, the code also gathers statistics and anomalies
(found during JSON validation and XML comparison). Statistics include:
- Min length, max length, total length of XML created
- Min count, max count, total count of records created
- Are records always created in chronological order?
- How many JSON validation errors and XML comparison differences? 

Anomalies in the XML comparison that are worked around include:
- empty element in `letter/printed` can be omitted (`NodeFilter`).
- some empty elements in `breathingSpace/*` can be omitted (`NodeFilter`).
- de-serialization of dates included a time, e.g. `2024-05-21` vs.
  `2024-05-21T00:00:00Z` (`XMLGregorianCalendarSerializer`).
- re-serialization of dates included a timezone, e.g.  `2024-05-21` vs.
  `2024-05-21Z` (`XMLGregorianCalendarDeserializer`).
- re-serialization of some numbers `0.0` < _N_ < `1.0` differ, e.g.
  `.76` vs. `0.76` (`DiffEval`).
- re-serialization of some numbers `-1.0` < _N_ < `0.0` differ, e.g.
  `-.1` vs. `-0.1` (`DiffEval`)

The code originally used Spring Data JDBC Pageable for pagination,
but the performance of this degrades significantly as you continue
to iterate many pages from the first matching record: The "skip
delay" varied from 0ms in the first page to over 100,000ms by about
the 100th page (of size 1000 records). The solution was to use
`findByIdBetween(startId, endId)` with a limit ordered by id, and
to keep track of the last id in each queried chunk.
