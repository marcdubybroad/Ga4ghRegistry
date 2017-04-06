# Ga4ghRegistry
**GA4GH Registry reference project**

**To build and run:**
 - Make sure you have Java 8: *java -version*
 - Make sure you have [Gradle](https://gradle.org/downloads). Use version 1.10 for now
 - Run *gradle build*
 - Then issue the command *java -jar build/libs/ga4gh-registry-0.0.1-SNAPSHOT.jar*
 - The server will run on port 8090, under the context root *registry*
   - http://localhost:8090/registry/peers
 
# APIs:
## /peers GET
Returns a list of server nodes

**Example output**
```json
{
    "peers": [
        {
            "type": "beacon",
            "url": "http://dude.org"
        },
        {
            "type": "ga4gh",
            "url": "http://test.com"
        }
    ]
}
```
 
## /peers POST
To add a new server node

**Example input**
```json
{"url": "http://ga4gh.org", "type": "beacon"}
```
 
## /peers PUT
To update an existing server node

**Example input**
```json
{"url": "http://ga4gh.org", "type": "matchmaker"}
```
 
## /peers DELETE
To delete an existing server node

**Example input**
```json
{"url": "http://ga4gh.org", "type": "beacon"}
```
  
