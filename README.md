### Pre-requirements

- Eclipse Neon (Release 4.6.0)
- JDK 7 (update 80)
- Apache Tomcat 8.0.41
- MySQL WorkBench 6.3.6 (build 511 CE)

### HOW TO

1. Clone the project from this repo
2. In Eclipse: `File` > `Open Projects from File System`
3. Select the project and click `Finish`
4. Right click on the project > `Properties` > `Java Build Path` > tab `Source`, add next directories to the project sources
	- `src/main/java`
	- `src/dev/java`
	- `src/test/java`
	- `src/main/webapp`
5. Click `Apply`
6. Navigate to `Project Facets`
7. Select `Dynamic Web Module - 3.1` and `Java - 1.7`
8. Click `Further configuration available`
9. On dialog window click `Next` > Specify `src/main/webapp` in `Content directory` field > `Ok` > `Apply` > `Ok`
10. Again, right click on the project > `Properties` > Navigate to `Deployment Assembly`
11. Make sure source's `/src/main/webapp` deploy path set to `/`
12. Click `Add`, select `Java Build Path Entries`, click `Next`, select `Maven Dependencies`, click `Finish` > `Apply` > `Ok`
13. Run the sql script from `external` folder to create a schema
14. Right click on the project > `Run as` > `Maven install`
15. To generate test data - run `TestDataFiller` from `src/dev/java` (*Note:* if you got next error - `Error: Could not find or load main class com.shop.product.testenv.TestDataFiller` - you need to click `Project` menu item > `Clean` > select `shop-product` > `Ok`. This action is needed because Maven cleans all compiled classes up but doesn't recompile classes not from [Maven directory structure](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html))
16. To start application - right click on the project > `Run as` > `Run on Server`
