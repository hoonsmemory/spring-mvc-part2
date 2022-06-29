package hoon.springmvc.typeconverter.converter;

import hoon.springmvc.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("converter source = {}", source);
        //127.0.0.1:8080
        String[] split = source.split(":");
        String ip = split[0];
        Integer port = Integer.valueOf(split[1]);

        return new IpPort(ip, port);
    }
}
