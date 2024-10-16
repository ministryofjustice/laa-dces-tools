# Verify Concor Contribution Records

Connects to the MAAT DB Schema and verifies that the XML in the
`CONCOR_CONTRIBUTIONS` table can be loaded and parsed using specific DTO
classes and that deserializing the original XML content into the DTO, then
re-serializing the DTO back into XML has comparable content.

Additional checks include serializing each DTO as JSON, and verifying that
the output JSON matches the JSON schema.

For each `CONCOR_CONTRIBUTIONS.FULL_XML` value in the database, perform the
following checks (and iterate using some form of paging so memory usage
remains constrained):

- Transform XML -> DTO
- Transform DTO -> JSON
- Validate the transformed JSON
- Transform JSON -> DTO
- Transform DTO -> XML
- Compare the transformed XML to the original XML

During theses processs,

* Gather statistics and anomalies (found during JSON validation and XML
  comparison). Potential statistics to gather:
    - min max avg length of XML per record (JSON) - variance?
    - min max avg #contributions created each day in any status
    - min max avg #contributions created each day in SENT
    - min max avg total length of SENT XML per day (JSON)
    - is same maat_id ever sent more than once per day? is rep_id same as maat_id?
    - are there sent records without a contrib-file-id?
* Work around the following XML comparison anomalies:
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
* Originally used Spring Data JDBC Pageable for pagination, but this slows
  down significantly as you iterate many pages from the first record:
    - Pagination takes increasing time as number of records to skip increases
      (<10ms/rec when skipping 0 records, >100ms/rec when skipping 100,000
      records).
    - The solution used is to use findByIdBetween(startId, endId) with a limit
      and keep track of ids in the code.
