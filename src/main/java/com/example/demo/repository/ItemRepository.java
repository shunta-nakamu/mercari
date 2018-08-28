package com.example.demo.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Items;

@Repository
public class ItemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final RowMapper<Items> ITEMS_ROW_MAPPER = (rs, i) -> {
		Items items = new Items();

		items.setId(rs.getInt("id"));
		items.setName(rs.getString("name"));
		items.setCondition(rs.getInt("condition"));
		items.setCategory(rs.getInt("category_id"));
		items.setBrand(rs.getString("brand"));
		items.setPrice(rs.getInt("price"));
		items.setShipping(rs.getInt("shipping"));
		items.setDescription(rs.getString("description"));
		String name_all = (rs.getString("category_name"));
		items.setName_all(name_all);

		String nameSplited[] = name_all.split("/");

		items.setName_parent(nameSplited[0]);
		items.setName_child(nameSplited[1]);
		items.setName_grand_child(nameSplited[2]);

		return items;
	};
	


	public List<Items> findAll(Integer status) {
		String sql = "select a.id as id , a.name as name, a.category as category_id, a.condition as condition, b.name_all as category_name, a.brand as brand,  a.price as price"
				+ ", a.shipping as shipping, a.description as description from items as a left outer join category as b on a.category = b.id order by id asc limit :limit offset :offset;";

		int limit = 30;
		int offset;
		if (status == 1) {
			offset = 0;
		} else {
			status -= 1;
			offset = status * 30;
		}

		SqlParameterSource param = new MapSqlParameterSource().addValue("limit", limit).addValue("offset", offset);

		List<Items> itemsList = namedParameterJdbcTemplate.query(sql, param, ITEMS_ROW_MAPPER);
		return itemsList;
	}

	public void insertItem(String category_name, String itemName) {

		String sql = "insert into items (name, condition, category, brand, price, shipping, description)select a.name as name, a.condition_id as condition, b.id as category, a.brand as brand, a.price as price, a.shipping as shipping, a.description as description from original as a inner join category as b on :category_name = b.name_all where a.name = :name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("category_name", category_name).addValue("name",
				itemName);
		namedParameterJdbcTemplate.update(sql, param);
	}

	public Items load(long itemId) {
		String sql = "select a.id as id , a.name as name, a.category as category_id, a.condition as condition, b.name_all as category_name, a.brand as brand,  a.price as price"
				+ ", a.shipping as shipping, a.description as description from items as a left outer join category as b on a.category = b.id where a.id=:itemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		Items items = namedParameterJdbcTemplate.queryForObject(sql, param, ITEMS_ROW_MAPPER);
		return items;

	}

	public void updateItem(Items item) {
		String sql = "update items set name = :name, condition =:condition, category=(select id from category where category.name_all = :name_all), brand = :brand, price =:price, shipping=:shipping, description=:description where id =:id;";
		SqlParameterSource pram = new MapSqlParameterSource().addValue("name", item.getName())
				.addValue("condition", item.getCondition()).addValue("brand", item.getBrand())
				.addValue("price", item.getPrice()).addValue("shipping", item.getShipping())
				.addValue("description", item.getDescription()).addValue("id", item.getId())
				.addValue("name_all", item.getName_all());
		System.out.println(item);

		namedParameterJdbcTemplate.update(sql, pram);
	}

	public List<Items> searchItems(String name, String brand, String parent, String child, String grandChild) {
		System.out.println("searchItems");
		
		String sql = "select a.id as id , a.name as name, a.category as category_id, a.condition as condition, b.name_all as category_name, a.brand as brand,  a.price as price"
				+ ", a.shipping as shipping, a.description as description from items as a left outer join category as b on a.category = b.id  where a.name like :name or a.brand like :brand or "
				+ "b.name_all = :parent and b.name_all = :child and b.name_all = :grandChild order by id asc limit 30 offset 0";

		String nameLike = "%" + name + "%";
		String brandLike = "%" + brand + "%";
		String parentLike = "%" + parent + "%";
		String childLike = "%" + child + "%";
		String grandChildLike = "%" + grandChild + "%";
		SqlParameterSource pram = new MapSqlParameterSource().addValue("name", nameLike).addValue("brand", brandLike).addValue("parent", parentLike).addValue("child", childLike).addValue("grandChild", grandChildLike);
		List<Items> itemList = namedParameterJdbcTemplate.query(sql, pram, ITEMS_ROW_MAPPER);
		return itemList;
	}

}
