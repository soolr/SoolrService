package com.soolr.api.dao.elasticsearch;

import com.soolr.api.entity.elasticsearch.EsLog;
import com.soolr.api.entity.elasticsearch.EsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @author Soolr
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {

}
