package si.kuharskimojster.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import si.kuharskimojster.entities.RecipeNode;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends Neo4jRepository<RecipeNode, Long> {

    @Query("MATCH (:Recipe {recipeId: {recipeId} })<-[r:RATED]-() RETURN avg(r.rating)")
    double getRecipeAvgRating(@Param("recipeId") Long recipeId);

    @Query("MATCH (:Recipe {recipeId: {recipeId} })<-[c:COMMENTED]-() RETURN c.comment LIMIT {limit}")
    List<String> getRecipeComments(@Param("recipeId") Long recipeId, @Param("limit") int limit);


}
