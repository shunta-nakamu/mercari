package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Sample;

@Repository
public class SampleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	private static final RowMapper<Sample> SAMPLE_ROW_MAPPER = (rs, i) -> {
		Sample sample = new Sample();
		sample.setId(rs.getInt("id"));
		sample.setName_big(rs.getString("name_big"));
		sample.setName_middle(rs.getString("name_middle"));
		sample.setName_small(rs.getString("name_small"));
		return sample;
	};
	
	public List<Sample> findAll (){
		List<Sample> sampleList = new ArrayList<>();
		String sql = "select id, name_big, name_middle, name_small from sample order by id asc;";
		
		try {
		sampleList = namedParameterJdbcTemplate.query(sql, SAMPLE_ROW_MAPPER);
		System.out.println(sampleList);
		System.out.println("test");
		return sampleList;	
		
		}catch(NullPointerException ex) {
			
			ex.printStackTrace();
			return sampleList;
		}
	}
}
