package com.example.mvmax.mindgames.games.guessthestory.model;

public class GuessTheStoryGameCompletedItemModel {

    private final String mUniqueId;

    public GuessTheStoryGameCompletedItemModel(final String pUniqueId) {
        mUniqueId = pUniqueId;
    }

    public String getUniqueId() {
        return mUniqueId;
    }

    public static final class Field {

        public static final String TABLE = GuessTheStoryGameCompletedItemModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String UNIQUE_ID = "unique_id";

    }
}
