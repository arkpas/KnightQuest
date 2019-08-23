package arkpas.kursspring.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassEncoder extends BCryptPasswordEncoder {

    public PassEncoder() {
        super();
    }

    public PassEncoder(int strenght) {
        super(strenght);
    }
}
