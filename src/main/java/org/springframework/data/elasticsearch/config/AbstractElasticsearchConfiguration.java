/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * @author Christoph Strobl
 * @author Peter-Josef Meisch
 * @since 3.2
 * @see ElasticsearchConfigurationSupport
 */
public abstract class AbstractElasticsearchConfiguration extends ElasticsearchConfigurationSupport {

	/**
	 * Return the {@link RestHighLevelClient} instance used to connect to the cluster. <br />
	 *
	 * @return never {@literal null}.
	 */
	@Bean
	public abstract RestHighLevelClient elasticsearchClient();

    /**
     * Creates {@link ElasticsearchOperations}. <br/>
     * NOTE: in version 4.1.2 the second parameter was added, previously this implementation called
     * {@link #elasticsearchClient()} directly. This is not possible anymore, as the base configuration classes don not
     * use proxied bean methods anymore.
     *
     * @param elasticsearchConverter the {@link ElasticsearchConverter} to use*
     * @param elasticsearchClient the {@link RestHighLevelClient} to use
     * @return never {@literal null}.
     */
	@Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
	public ElasticsearchOperations elasticsearchOperations(ElasticsearchConverter elasticsearchConverter,
			RestHighLevelClient elasticsearchClient) {
		return new ElasticsearchRestTemplate(elasticsearchClient, elasticsearchConverter);
	}
}
