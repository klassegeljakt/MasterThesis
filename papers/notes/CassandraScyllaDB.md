# Suitability of NoSQL systems—Cassandra and ScyllaDB—for IoT workloads

Keywords: Cassandra (Java), ScyllaDB (C++), re-write, caching

NoSQL is a new breed of high performance data management systems. They store data in more flexible formats than the regular tabular format found in SQL systems. As a result, they are both able to store more data, and are able to read and write it faster. One of the leading NoSQL data stores is Cassandra, developed by Facebook. Cassandra is written in Java and provides a highly customizable and decentralized architecture. Following Cassandra's success came ScyllaDB. ScyllaDB is an open-source re-write of Cassandra into C++ code with focus on utilization of multi-core architectures, and abolishing the JVM overhead. Most of Cassandra's logic is retained in ScyllaDB. One notable difference is their caching mechanisms. Caching reduces the disk seeks on read operations. This helps decrease the I/O usage which can be a major bottleneck in distributed storage systems. Unlike Cassandra's cache, ScyllaDB's cache is dynamic. ScyllaDB will allocate all available memory to its cache and dynamically evict entries if memory is required for other tasks. This would be less feasible in Cassandra where memory is managed by the garbage collector. In evaluation, ScyllaDB's caching strategy improved the reading performance by less cache misses, but also had a negative impact on write performance.
