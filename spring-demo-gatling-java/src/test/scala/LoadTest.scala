import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.language.postfixOps
import scala.util.Random

class LoadTest extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")
    .header("Content-Type", "application/json")

  val random = new util.Random

  val feeder = Iterator.continually(Map(
    "dynamicId" -> (Random.nextInt(Integer.MAX_VALUE - 1) + 1).toString,
    "dynamicName" -> random.alphanumeric.take(10).mkString
  ))

  object CustomerResource {
    val get: ChainBuilder = exec(http("GetCustomer")
      .get("/customer")
      .header("id", "1"))
    val post: ChainBuilder = feed(feeder).exec(http("CreateCustomer")
      .post("/create")
      .body(StringBody("""{"id": "${dynamicId}", "name": "${dynamicName}"}"""))
      .asJson
      .check(status.is(200)))
  }

  val myScenario: ScenarioBuilder = scenario("CustomerEndpoints")
    .exec(CustomerResource.get)
    .pause(1 second) // Add a pause between requests for more realistic simulation
    .exec(CustomerResource.post)

  setUp(myScenario.inject(
    rampUsersPerSec(10) to 200 during (30 seconds),
    constantUsersPerSec(200) during (60 seconds)
  )).protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.is(100))
}