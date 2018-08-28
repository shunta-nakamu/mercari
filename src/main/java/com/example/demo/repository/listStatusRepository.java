package com.example.demo.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ListStatus;

/**
 * list_statusに接続するリポジトリクラス.
 * @author Shunta
 *
 */
@Repository
public class listStatusRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final RowMapper<ListStatus> LIST_STATUS_ROW_MAPPER=(rs,i)->{
		ListStatus listStatus = new ListStatus();
		listStatus.setStatus(rs.getInt("status"));
		return listStatus;
	};
	
	/**
	 * ページのステータスを取ってくるメソッド.
	 * @return　ページのステータス
	 */
	public ListStatus getStatus() {
		String sql = "select status from listStatus;";
		ListStatus listStatus = jdbcTemplate.queryForObject(sql, LIST_STATUS_ROW_MAPPER);
		return listStatus;
	}
	
	/**
	 * statusを変更するメソッド.
	 * @param status 編集済みのstatusを追加する
	 */
	public void updateStatus(int status) {
		String sql = "update listStatus set status=:newStatus;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("newStatus", status);
		namedParameterJdbcTemplate.update(sql, param);
	}
}
