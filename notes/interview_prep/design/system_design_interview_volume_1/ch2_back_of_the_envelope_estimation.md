# Power of two

# Latency numbers every programmer should know

Year 2020

* L1 cache reference - 0.5ns
* Branch mispredict - 5ns
* L2 cache reference- 7ns
* Mutex lock/unlock - 100ns
* Main memory reference - 100ns
* Compress 1k bytes with Zipy - 10us
* Send 2k bytes over 1 Gbps network - 20us
* Ready 1 MB sequentially from memory - 250us
* Round trip within the same datacenter - 500us
* Disk seek - 10ms
* Read 1 MB sequentially from the network - 10ms
* Read 1 MB sequentially from disk - 30ms
* Send packet CA(California)->Netherlands->CA - 150ms

Conclusions:

* Memory is fast but disk is slow.
* Avoid disk seeks if possible.
* Simple compression algorithms are fast.
* Compress data before sending it over the internet if possible.
* Data centers are usually in different regions, and it takes time to send data between them.

# Availability numbers

* 99% - 14.40 minutes downtime per day
* 99.9% - 1.44 minutes downtime per day
* 99.99% - 8.64 seconds downtime per day
* 99.999% - 864 milliseconds downtime per day
* 99.9999% - 86.4 milliseconds downtime per day

# Example: Estimate Twitter QPS and storage requirements

# Tips

* Rounding and approximation. It is difficult to perform complicated math operations during the interview. For example,
  what is the result of 99987/9.1 There is no need to spend valuable time to solve complicated math problems Precision
  is not expected. Use round numbers and approximation to your advantage. The division question can be simplified as
  follows: 100000/10
* Write down your assumptions. It is a good idea to write down your assumptions to be referenced later.
* Label your units. Whn you write down "5", does it mean 5 KB or 5 MB? You might confuse yourself with this. Write down
  units because "5 MB" helps to remove ambiguity.
* Commonly asked back-of-the-envelope estimations: QPS, peak QPS, storage, cache, number of servers, etc. You can
  practice these calculations when preparing for an interview. Practice makes perfect.