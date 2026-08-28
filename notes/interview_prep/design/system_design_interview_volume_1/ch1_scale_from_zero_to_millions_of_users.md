# Single server setup

# Database

Relational databases represent and store data in tables and rows. You can perform join operations using SQL across
different database tables.

These databases are grouped into four categories: key-value stores, graph stores, column stores, and document stores.

Non-relational database is a good choice if:

* Your application requires super-low latency
* Your data are unstructured, or you do not have any relational data
* You only need to serialize and deserialize data
* You need to store a massive amount of data

# Vertical scaling and horizontal scaling

Vertical scaling, referred to as "scale up", means the process of adding more power (CPU, RAM, etc.) to your servers.
Horizontal scaling, referred to as "scale-out", allows you to scale by adding more servers into your pool of resources.

When traffic is low, vertical scaling is a great option, and the simplicity of vertical scaling is its main advantage.
Unfortunately, it comes with serious limitations.

* Vertical scaling has a hard limit. It is impossible to add unlimited CPU and memory to a single server.
* Vertical scaling does not have failover and redundancy. If one server goes down, the website/app goes down with it
  completely.

# Load balancer

For better security, private IPs are used for communication between servers. A private IP is an IP address reachable
only between servers in the same network; however, it is unreachable over the internet. THe load balancer communicates
with web servers through private IPs.

# Database replication

A master database generally only supports write operations. A slave database gets copies of the data from the master
database and only supports read operations. All the data-modifying commands like insert, delete, or update must be sent
to the master database. Most applications require a much higher ratio of reads to writes; thus, the number of salve
databases in a system is usually larger than the number of master databases.

# Cache

* Decide when to use cache. Consider using cache when data is read frequently but modified infrequently. Since cached
  data is stored in volatile memory, a cache server is not ideal for persisting data. For instance, if a cache server
  restarts, all the data in memory is lost. Thus, important data should be saved in persistent data stores.
* Expiration policy. It is a good practice to implement an expiration policy. Once cached data is expired, it is removed
  from the cache. When there is no expiration policy, cached data will be stored in the memory permanently. It is
  advisable not to make the expiration date too short as this will cause the system to reload data from the database too
  frequently. Meanwhile, it is advisable not to make the expiration data too long as the data can become stale.
* Consistency: This involves keeping the data store and the cache in sync. Inconsistency can happen because
  data-modifying operations on the data store and cache ar enot in a single transaction. When scaling across multiple
  regions, maintaining consistency between the data store and cache is challenging.
* Mitigating failures: A single cache server represents a potential single point of failure. As a result, multiple cache
  servers across different data centres are recommended to avoid SPOF. Another recommended approach is to overprovision
  the required memory by certain percentages. This provides a buffer as the memory usage increases.
* Eviction policy: Once the cache is full, any requests to add items to the cache might cause existing items to be
  removed. THis is called cache eviction. Least-recently-used (LRU) is the most popular cache eviction policy. Other
  eviction policies, such as the Least Frequently Used (LFU) or First in First Out (FIFO), can be adopted to satisfy
  different use cases.

# Content delivery network (CDN)

A CDN is a network of geographically dispersed servers used to deliver static content. CDN servers cache static content
like images, videos, CSS, JavaScript files, etc.

1. User A tries to get image. png by using an image URL. The URL's domain is provided by the CDN provider. The following
   two image URLs are samples used to demonstrate what image URLs look like on Amazon and Akamai CDNs:
    * https://mysite.cloudfrnont.net/logo.jpg
    * https://mysite.akami.com/image-manager/img/logo.jpg
2. If the CDN server does not have image.png in the cache, the CDN server requests the file from the origin, which can
   be a web server or online storage like Amazon S3.
3. THe origin returns image.png to the CDN server, which includes optional HTTP header Time-to-Live(TTL) which describes
   how long the image is cached.
4. The CDN caches the image and returns it to User A. The image remains cached in the cDN until the TTL expires.
5. User B sends a request to get the same image.
6. The image is returned from the cache as long as the TTL has not expired.

Considerations of using a CDN

* Cost: CDNs are run by third-part providers, and you are charged for data transfers in and out of the CDN. Caching
  infrequently used assets provides no significant benefits so you should consider moving them out of the CDN.
* Setting an appropriate cache expiry: For time-sensitive content, setting a cache expiry time is important. The cache
  expiry time should neither expiry time is important. The cache expiry time should neither be too long nor too short.
  If it is too long, the content might no longer be fresh. If it is too short, it can cause repeat reloading of content
  from origin servers to the CDN.
* CDN fallback: You should consider how your website/application copes with CDN failure. If there is a temporary CDN
  outage clients should be able to detect the problem and request resources from the origin.
* Invalidating files: You can remove a file from the CDN before it expires by performing one of the following
  operations:
    * Invalidate the CDN object using APIs provided by CDN providers
    * Use object versioning to serve a different version of the object. To version an object, you can add a parameter to
      the URL, such as version number. For example, version number 2 is added to the query string: image.png?v=2.

# Stateless web tier

# Data centers

* Traffic redirection: Effective tools are needed to direct traffic to the correct data center. GeoDNS can be used to
  direct traffic to the nearest data center depending on where a user is located.
* Data synchronization: Users from different regions could use different local databases or caches. In failover cases,
  traffic might be routed to a data center where data is unavailable. A common strategy is to replicate data across
  multiple data centers.
* Test and deployment: With multi-data center setup, it is important to test your website/application at different
  locations. Automated deployment tools are vital to keep services consistent through all the data centers.

# Message queues

# Logging, metrics, automation

Automation: When a system gets big and complex, we need to build or leverage automation tools to improve productivity.
Continuous integration is a good practice, in which each code check-in is verified through automation, allowing teams to
detect problems early. Besides, automating your build, test, deploy process, etc. could improve developer productivity
significantly.

# Database scaling

There are two broad approaches for database scaling: vertical scaling and horizontal scaling.

Horizontal scaling, also known as sharding, is the practice of adding more servers.

When choosing a sharding key, one of the most important criteria is to choose a key that can evenly distributed data.

Sharding is a great technique to scale the database but it is far form a perfect solution. It introduces complexities
and new challenges to the system:

* Resharding data: Resharding data is needed when
    * A single shard could no longer hold more data due to rapid growth
    * Certain shards might experience shard exhaustion faster than others due to uneven data distribution. When shard
      exhaustion happens, it requires updating the sharding function and moving data around. Consistent hashing is a
      commonly used technique to solve this problem.
* Celebrity problem: This is also called a hotspot key problem. Excessive access to a specific shard could cause server
  overload. Imagine data for Katy Perry, Justin Bieber, and Lady Gaga all end up on the same shard. For social
  application, that shard will be overwhelmed with read operations. To solve this problem, we may need to allocate a
  shard for each celebrity. Each shard might even require further partition.
* Join and de-normalization: Once a database has been sharded across multiple servers, it is hard to perform join
  operations across database shards. A common workaround is to de-normalize the database so that queries can be
  performed in a single table.