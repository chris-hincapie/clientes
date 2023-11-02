package com.gml.prueba.clientes.infrastructure.configuration;

import com.gml.prueba.clientes.domain.api.IClientServicePort;
import com.gml.prueba.clientes.domain.spi.IClientPersistencePort;
import com.gml.prueba.clientes.domain.usecase.ClientUseCase;
import com.gml.prueba.clientes.infrastructure.output.jpa.adapter.ClientJpaAdapter;
import com.gml.prueba.clientes.infrastructure.output.jpa.mapper.ClientEntityMapper;
import com.gml.prueba.clientes.infrastructure.output.jpa.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Bean
    public IClientPersistencePort clientPersistencePort(){
        return new ClientJpaAdapter(clientRepository, clientEntityMapper);
    }

    @Bean
    public IClientServicePort clientServicePort(){
        return new ClientUseCase(clientPersistencePort());
    }
}
