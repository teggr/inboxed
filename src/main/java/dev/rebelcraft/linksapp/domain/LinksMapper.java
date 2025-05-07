package dev.rebelcraft.linksapp.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LinksMapper {

    @Insert("INSERT INTO links (url) VALUES (#{url})")
    void insertLink(Link link);

    @Select("SELECT * FROM links")
    List<Link> getAllLinks();

    @Select("SELECT * FROM links WHERE url = #{url}")
    Link getLinkByUrl(String url);

    @Update("UPDATE links SET url = #{url} WHERE id = #{id}")
    void updateLink(Link link);
    
}
