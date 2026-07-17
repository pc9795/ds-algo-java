# Relational vs. document databases today

The main arguments in favor of the document data model are: for some applications it is closer to the data structures
used by the application, schema flexibility, and better performance due to locality. The relational model counters by
providing better support for joins, many-to-one and many-to-many relationships.

## Schema flexibility in the document model

The schema-on-read approach is advantageous if the data is heterogeneous, i.e. the items in the collection don’t all
have the same structure for some reason, for example because:

* there are many different types of objects, and it is not practical to put each type of object in its own table, or
* the structure of the data is determined by external systems, over which you have no control, and which may change at
  any time.
