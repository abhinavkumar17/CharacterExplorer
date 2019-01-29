package character.component.com.characterexplorer.common;

import character.component.com.characterexplorer.model.CharactersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterInterceptor {

    @GET("v1/public/characters")
    Call<CharactersResponse> getCharacters(@Query("apikey") String apikey, @Query("hash") String hash, @Query("limit") int limit, @Query("ts") int ts);

    @GET("v1/public/characters/{Id}")
    Call<CharactersResponse> getCharacterDetails(@Path("Id") String characterId,
                                           @Query("apikey") String apikey, @Query("hash") String hash,
                                           @Query("limit") int limit, @Query("ts") int ts);
}
