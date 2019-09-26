package pluralsight.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator {

	int errorcode=0;
	@Override
	public Health health() {
		Health health=null;
		// TODO Auto-generated method stub
		System.out.println("Error Code: "+errorcode);
		if(errorcode>=4&&errorcode<=8) {
			
			health = Health.down().withDetail("Custom Error Code", errorcode).build();
		}
		else {
			
			health=Health.up().build();
		}
		errorcode++;
		return health;
	}

}
