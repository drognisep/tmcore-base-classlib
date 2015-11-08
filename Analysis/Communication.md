# Communication Analysis

**Objective** - The creation of the base methods of communication between application objects and a centralized synchronization server/middleware

**Problems being solved with this module**
* The need for base communication between client and middleware/server
* The need for different methods for different requirements
  * **TCP** - Used for realtime human communication
    * Should be able to fail over to UDP or a tertiary method
  * **UDP Callback** - Used for simple sync and update request based features (Tasks). TCP may fallback to this functionality depending on the device preferences, like a mobile device with a limited data allotment.
    * Decided to provide a means of UDP communication, but actual callback must be implemented by individual features that require it.

**Future Developments**
* Creation of a user to user communication method that can fail over to SMTP, supports the upgrade path to utilize the system on mobile devices.
* Allow the use of SMS as a failover method
