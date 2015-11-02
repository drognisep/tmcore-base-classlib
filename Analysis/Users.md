# Users Analysis

**Objective** - The creation of the base data structures necessary to store, authenticate, and regulate profiles and activity

**Problems being solved with this module**
* The need for different classes of user accounts
  * **Super** - This user should only be available during development
    * no authorization checks are made against this user class
  * **Admin**
    * view system log files
    * add/remove/modify users
    * add/remove/modify user templates
    * create/assign/remove custom 'Capabilities' for registered expansions
    * change server configuration
    * add/remove/modify entries in maintenance log
  * **Support**
    * view system log files
    * view user data
    * view server configuration information
    * add/view maintenance log
  * **General**
    * view personal user data
    * run registered expansions for which the user has the necessary capabilities
* The need to group users together into a logical unit that can be used as a means to reference, and to assign blanket policies to, multiple users

**Future Developments**
* Development of expansion framwork as a means of extending the capability of the base functions *(separate module)*
* Allow Admin class users to create, require, and apply custom 'Capability' tokens to expansions as a means of extending and revoking a user or group's ability to use it.
