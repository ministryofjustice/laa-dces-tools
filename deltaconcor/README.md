# Delta Concor Contribution Records

Produce a CSV report detailing when changes to CONTRIBUTIONS data occurred (and which XML elements were changed).

The columns of the produced CSV file correspond to the elements in the XML schema, and the rows correspond to days
when the change occurred.

## Algorithm

#### Preliminaries
* Keep a `Set<Long>` of repId/maatId that we are missing.
* Keep a `Map<Long, CONTRIBUTIONS>` of maatId -> previous CONTRIBUTIONS data for that maatId, starts empty.
* Keep a `List<Change>` of `flag='new'` data for each day
* Keep a `List<Change>` of `flag='update'` data for each day
  - These Lists will be used to generate the CSV reports.
* Translate the start date and end date to a start ID and end ID.

#### Pass one (find maatIds that we need previous CONTRIBUTIONS for)
* Walk forward from the start ID to the end ID over `status='SENT` rows to find maatIds we don't have:
  * If it is `flag='new'`, then add the contribution to the `Map`.
  * If it is `flag='update'` and the maatId is not in the `Map`, then add it to the `Set`.

#### Pass two (back-fill the map of previous CONTRIBUTIONS data for the missing maatIds)
* Walk backward from the start ID over `status='SENT'` rows: 
  * Populate the `Map` of maatId -> previous CONTRIBUTIONS data for each missing maatId.
  * Remove from the `Set`.
  * Stop when the `Set` is empty.
* Alternate strategy for the last few maatIds that take all the time:
  * Query for rows that have maatIds in the `Set`, ordered by id descending, with a limit.
  * Work with the results as above.
  * Next time round, update the query to include only the smaller `Set` of maatIds that remain to back-fill.

### Pass three (generate the daily CSV data)
* Walk forward from the start ID to the end ID over `status='SENT'` rows:
  - If it is `flag='new'`, then:
    - Compare the current CONTRIBUTIONS data with null data.
    - Update the `List<Change>` with `flag='new'` data for that day.
    - Update the `Map` with the current CONTRIBUTIONS data.
  - If it is `flag='update'`, then:
    - Compare the current CONTRIBUTIONS data with the previous CONTRIBUTIONS data from the `Map`.
    - Update the `List<Change>` with `flag='update'` data for that day.
    - Update the `Map` with the current CONTRIBUTIONS data.

## Near future changes
* Revisit how to compare "array aggregate" (e.g. `breathingSpace`) and "array leaf" (e.g. `registration`) elements in 
  the CONTRIBUTIONS data. Just comparing the count of elements is not sufficient for most of these (but might be OK for
  `letter` and `ccOutcome` which are generally append-only).
