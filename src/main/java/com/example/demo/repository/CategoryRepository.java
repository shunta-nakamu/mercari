package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;
import com.example.demo.domain.Child;
import com.example.demo.domain.GrandChild;
import com.example.demo.domain.Parent;

/**
 * categoryテーブルに接続するリポジトリ.
 * 
 * @author Shunta
 *
 */
@Repository
public class CategoryRepository {

	int count = 0;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * Categoryドメインを返すローマッパ
	 */
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setName_all(rs.getString("name_all"));
		category.setParent(rs.getInt("parent"));
		return category;
	};
	
	private static RowMapper<Parent> PARENT_ROW_MAPPER=(rs, i)->{
		Parent parent = new Parent();
		parent.setParent(rs.getString("parent"));
		return parent;
	};
	private static RowMapper<Child> CHILD_ROW_MAPPER=(rs, i)->{
		Child child = new Child();
		child.setChild(rs.getString("child"));
		return child;
	};
	private static RowMapper<GrandChild> GRAND_CHILD_ROW_MAPPER=(rs, i)->{
		GrandChild grandChild = new GrandChild();
		grandChild.setGrandChild(rs.getString("grand_child"));
		return grandChild;
	};
	
	

	/**
	 * 全件検索するメソッド.
	 * 
	 * @return categoryのリスト
	 */
	public List<Category> findCategory() {
		String sql = "select name_all, id, parent, name from category order by id asc;";
		List<Category> categoryList = namedParameterJdbcTemplate.query(sql, CATEGORY_ROW_MAPPER);
		return categoryList;
	}

	/**
	 * 名前で検索をするメソッド.
	 * 
	 * @param name
	 *            名前
	 * @return categoryのリスト
	 */
	public List<Category> findByName(String name) {
		String sql = "select name_all, id, parent, name from category where name =:name and parent is null order by id asc;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);

		List<Category> categoryList = namedParameterJdbcTemplate.query(sql, param, CATEGORY_ROW_MAPPER);

		return categoryList;
	}

	/**
	 * 子カテゴリーを探すメソッド.
	 * @param name
	 * @return
	 */
	public List<Category> findByNameMiddleName(String name, int parent) {
		String sql = "select name_all, id, parent, name from category where name =:name and parent = :parent and name_all is null order by id asc;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("parent", parent);

		List<Category> categoryList = namedParameterJdbcTemplate.query(sql, param, CATEGORY_ROW_MAPPER);

		return categoryList;
	}

	/**
	 * 孫カテゴリーを探すメソッド.
	 * @param name
	 * @param name_all
	 * @return
	 */
	public List<Category> findByNameAndNameAll(String name, String name_all) {
		String sql = "select name_all, id, parent, name from category where name =:name and name_all=:name_all order by id asc;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("name_all", name_all);

		List<Category> categoryList = namedParameterJdbcTemplate.query(sql, param, CATEGORY_ROW_MAPPER);

		return categoryList;
	}

	/**
	 * insertするメソッド.
	 * 
	 * @param category
	 *            categoryオブジェクト
	 */
	public void setCategory(Category category) {

		System.out.println("count = " + count);
		count++;
		String sql = "insert into category (name_all, name, parent) values(:name_all, :name, :parent)";

		String name_all = category.getName_all();
		String name = category.getName();
		Integer parent = category.getParent();

		SqlParameterSource param = new MapSqlParameterSource().addValue("name_all", name_all).addValue("name", name)
				.addValue("parent", parent);

		namedParameterJdbcTemplate.update(sql, param);
	}

	/**
	 * idで削除するメソッド.
	 * 
	 * @param id
	 * 
	 */
	public void deleteCalamById(int id) {
		String sql = "delete from category where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		namedParameterJdbcTemplate.update(sql, param);
	}
	
	public List<Parent> findParent(){
		String sql = "select distinct name as parent from category where parent is null order by name asc;";
		List<Parent> list = jdbcTemplate.query(sql, PARENT_ROW_MAPPER);
		return list;
	}
	public List<Child> findChild(){
		String sql = "select distinct name as child from category where parent is not null and name_all is null order by name asc;";
		List<Child> list = jdbcTemplate.query(sql, CHILD_ROW_MAPPER);
		return list;
	}
	public List<GrandChild> findGrandChild(){
		String sql = "select distinct name as grand_child from category where parent is not null and name_all is not null order by name asc;";
		List<GrandChild> list = jdbcTemplate.query(sql, GRAND_CHILD_ROW_MAPPER);
		return list;
	}
	

}
