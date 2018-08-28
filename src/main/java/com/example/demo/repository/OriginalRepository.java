package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Items;
import com.example.demo.domain.Original;

/**
 * originalテーブルに接続するリポジトリ.
 * 
 * @author Shunta
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Originalドメインを返すローマッパ
	 */
	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = (rs, i) -> {
		Original original = new Original();
		original.setId(rs.getInt("id"));
		original.setName(rs.getString("name"));
		original.setCondition_id(rs.getInt("condition_id"));
		original.setCategory_name(rs.getString("category_name"));
		original.setBrand(rs.getString("brand"));
		original.setPrice(rs.getInt("price"));
		original.setShipping(rs.getInt("shipping"));
		original.setDescription(rs.getString("description"));
		return original;

	};

	/**
	 * originalの要素を全件検索するメソッド.
	 * 
	 * @return originalテーブルのリスト
	 */
	public List<Original> findAllOfOriginal() {
		String sql = "select id, name, condition_id, category_name, brand, price, shipping, description from original order by id asc;";
		List<Original> originalList = jdbcTemplate.query(sql, ORIGINAL_ROW_MAPPER);

		return originalList;
	}
	
	public String findById(int id) {
//		System.out.println(id);
		String sql = "select category_name from original where id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
		String category_name = namedParameterJdbcTemplate.queryForObject(sql, param, String.class);
		return category_name;
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String findNameById(int id) {
		String sql = "select name from original where id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String name = namedParameterJdbcTemplate.queryForObject(sql, param, String.class);
		return name;
	}
	
	public Integer findMaxId() {
		String sql = "select max(id) from original;";
		Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
		return maxId;
	}
	public void insert(Items items) {
		String sql = "insert into original(id, name, condition_id, category_name, brand, price, description)"
				+ "values (:id, :name, :condition_id, :category_name, :brand, :price, :description);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", items.getId())
															   .addValue("name", items.getName())
															  .addValue("condition_id", items.getCondition())
															  .addValue("category_name", items.getName_all())
															  .addValue("brand", items.getBrand())
															  .addValue("price", items.getPrice())
															  .addValue("description", items.getDescription());
		namedParameterJdbcTemplate.update(sql, param);
	}
	
	public boolean deleteById(int id) {
		String sql = "delete from items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		namedParameterJdbcTemplate.update(sql, param);
		return true;
	}
	
	
}
