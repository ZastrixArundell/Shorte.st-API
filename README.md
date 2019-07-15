# Shorte.st-API
Java api for Shorte.st shorted URL's

## How does it work
The web api for shorte.st only works with CURL. I added the ***-v*** argument
to the CURL request to get a list of the used headers and based off from that
I made a HTTPS request with those headers. Basically Java is imitating a CURL
request.  

## Usage

### Adding in maven
To add this in maven you need to add this repository:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
And then add this dependency:
```xml
<dependency>
    <groupId>com.github.ZastrixArundell</groupId>
    <artifactId>Shorte.st-API</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Example code
```java
public class Main
{

    public static void main(String[] args)
    {
        ShortestAPI api = new ShortestAPI("yourtokenurl");
        api.setLink("https://facebok.com");

        try
        {
            api.sendRequest();
        }
        catch (IOException oof)
        {
            System.out.println("Oof: " + oof.getLocalizedMessage());
        }

        System.out.println("JSON message: " + api.getReponseJson());
        System.out.println("Status: " + api.getStatusMessage());
        System.out.println("ShortenedURL: " + api.getShortenedURL());
    }
}
```
