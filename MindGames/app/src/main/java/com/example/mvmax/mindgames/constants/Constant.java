package com.example.mvmax.mindgames.constants;

public class Constant {

    public interface FilePath {

        String GAMES_PATH = "games.json";

    }

    public interface GuessTheStory {

        String STORIES_PATH = "guess_the_story_list.json";
    }

    public interface GameID {

        String GUESS_THE_STORY = "game.id:guess_the_story";
        String GUESS_THE_WORD = "game.id:guess_the_word";

    }

    public interface GoogleAuth {
        int SIGNED_IN = 0;
        int STATE_SIGNING_IN = 1;
        int STATE_IN_PROGRESS = 2;
        int RC_SIGN_IN = 0;

    }

    public interface SharedPreferences {
        String SHARED_PREF_NAME = "shared_pref_name";
        String USERNAME = "shared_pref_username";
        String URL_PHOTO = "shared_pref_url_photo";
        String EMAIL = "shared_pref_email";

    }
}
