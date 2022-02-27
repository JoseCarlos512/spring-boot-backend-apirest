package com.dev.spring.boot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEAySOJlEZls4tQQpjq3m/u61RHBUFJsDq3OXrmcPI1XU/5atoE\r\n"
			+ "Y+cw0ASQoWdfdJBOAwVVGpTSzTRFlTMICcNG8qRwQX9DMRxOzME0x4XCYsS6WmY2\r\n"
			+ "3Xj+STryLjPomzrKDrRw0+kKXUg7WtHxOnZ44I0kcEucHl4zA+0xv+IJsRvGPhLg\r\n"
			+ "kxnjmlYsjjwzkRzrJbtJQB5HsF2Ugwvt34De2ipHUAI+AOc400SqtbJJoZyyDSe4\r\n"
			+ "pFHuPFyhABSOJfBYENJitQt+5PyeE3POA8GIoizo9UmX3PTFdSzHzM2wGpqaS0nP\r\n"
			+ "EDKyAX1L9YcXvvD0ghk41ouMg8E6CX9/Z8LTfwIDAQABAoIBAQC+h2tLiWPNL6pX\r\n"
			+ "a4MQMfef87VSxQWIahYl3MVtOAJU4ezhyqNd/yG96pUWT1WclRVQxzxWcaM9vsYr\r\n"
			+ "DqwOk2whbKLpaCO94ZLDYkEcvxfjd3cPKsPOB07ZD31ZRtKQOKAuTb6S/a1Tr5iJ\r\n"
			+ "71USjCb0jr51oSnBmnm4vnbhzLxhhr+OWwwmhI1b7NzYfftcEVltPKsMK1Id2WA7\r\n"
			+ "qDQERbvVxiMoSrAQuvU2clUf87cdMrKwqeXCrDeFdpZtDNrEw8fZjeIrQqDnrMw0\r\n"
			+ "ZJTRBoB4D8xw2u37KxvX0aaMR3MM5sFngl867w1+B+0pL9sFgbFDbxNdHRkKw7pT\r\n"
			+ "vigtEsQBAoGBAOubf1NJZTW6LzcjZqxOX34mfLA3e7n+pFS5rsFhs0d8XIaPwE7f\r\n"
			+ "wieKflVVF+JZYdK59Uq33rFEXB68QxyrM6p8bZom8xkLsoouN3YqoWFxwcgq/DaP\r\n"
			+ "b8usblt4H7cnJ1Zo3K8FWxAVvskSd7a0XgKoKBDwPdN7y+y2ayMc1+m1AoGBANqM\r\n"
			+ "TC9Tqm+4lqAmXmtxIxPMezLcd/AY//yqL9jo+riBsDkre6GwRkvvJMSs1r7VQOB/\r\n"
			+ "rmDO0Td8TGbvOBMwL7GubpDlkG4epKbCuap/R+63eicfHxE+ovepJmnVBIpJHfX4\r\n"
			+ "UNuy5hz8BMyU0iICRi7Ljp7gTC4LzF+ztE+10zjjAoGBAIRiIvDRVKUTsGPy6EX4\r\n"
			+ "438mcPkys7qWcg+JJcBS/UE1h2e0bgHjiFxfXQzlez4bqvOUuVb6aY6BMqGydqMt\r\n"
			+ "LyKkN5FeQUnZYMBzp4Yl/feay5+FR6IAhniSso+Ct49Z2Kic0yMkhz5KeaDRV6K5\r\n"
			+ "RfmIMrsRwiuKa9LdzOU117vhAoGAMjpIUCA0bZTI2Wm4DY1k+GnobpoI3p+ARbt/\r\n"
			+ "X6S76qezro/1zAVNQqplkuX+PMGaNX5iv1EI+G2SyZ11D8PlUjq6ooaAGS7lvIXI\r\n"
			+ "Ug0KpSx54H/gjgANSEoR0ATrFDUVs7AsNTH6fPLWz4XsfXB0XNDfqFf4aiTABcax\r\n"
			+ "TBiHwO0CgYAfACT+ebpmdcDUDDBu3s6EScH3rjInV4TONG6zqqVFsazQVTO27v1O\r\n"
			+ "1QedjyilpE3kvC5uBN6qH52wHzdt0zgLJweaS+8E5wzSCy26zPnI6lzdM0kVqkfN\r\n"
			+ "BuHcufDur4GvU627Kn9BNl4n+Ign7mR/g0LKi3qvj2yjCCNgXmRJTw==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAySOJlEZls4tQQpjq3m/u\r\n"
			+ "61RHBUFJsDq3OXrmcPI1XU/5atoEY+cw0ASQoWdfdJBOAwVVGpTSzTRFlTMICcNG\r\n"
			+ "8qRwQX9DMRxOzME0x4XCYsS6WmY23Xj+STryLjPomzrKDrRw0+kKXUg7WtHxOnZ4\r\n"
			+ "4I0kcEucHl4zA+0xv+IJsRvGPhLgkxnjmlYsjjwzkRzrJbtJQB5HsF2Ugwvt34De\r\n"
			+ "2ipHUAI+AOc400SqtbJJoZyyDSe4pFHuPFyhABSOJfBYENJitQt+5PyeE3POA8GI\r\n"
			+ "oizo9UmX3PTFdSzHzM2wGpqaS0nPEDKyAX1L9YcXvvD0ghk41ouMg8E6CX9/Z8LT\r\n"
			+ "fwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----"; 
}
