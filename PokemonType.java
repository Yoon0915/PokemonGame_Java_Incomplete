import java.util.Arrays;

public class Pokemon {
    private static final int NUMBER_OF_TYPE = 15;

    public enum PokemonType {
        NORMAL,
        FIRE,
        WATER,
        GRASS,
        ELECTR,
        ICE,
        FIGHT,
        POISON,
        GROUND,
        FLYING,
        PSYSHC,
        BUG,
        ROCK,
        GHOST,
        DRAGON
    }

    private static final float POOR = 0.5f;
    private static final float NICE = 2.0f;
    private static final float JUST = 1.0f;
    private static final float DONT = 0.0f;

    private static final float[][] typeMatchingTable = new float[][]{
            // NORMAL  FIRE   WATER  GRASS  ELECTR ICE    FIGHT  POISON GROUND FLYING PSYSHC BUG    ROCK   GHOST  DRAGON
            {JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, POOR, DONT, JUST},
            {JUST, POOR, POOR, NICE, JUST, NICE, JUST, JUST, POOR, JUST, JUST, NICE, POOR, JUST, POOR},
            {JUST, NICE, POOR, POOR, JUST, JUST, JUST, JUST, NICE, JUST, JUST, JUST, NICE, JUST, POOR},
            {JUST, POOR, NICE, POOR, JUST, JUST, JUST, POOR, NICE, POOR, JUST, JUST, NICE, JUST, POOR},
            {JUST, JUST, NICE, POOR, POOR, JUST, JUST, JUST, DONT, NICE, JUST, JUST, JUST, JUST, POOR},
            {JUST, JUST, POOR, NICE, JUST, POOR, JUST, JUST, NICE, NICE, JUST, JUST, JUST, JUST, NICE},
            {NICE, JUST, JUST, JUST, JUST, NICE, JUST, POOR, JUST, POOR, POOR, POOR, NICE, DONT, JUST},
            {JUST, JUST, JUST, NICE, JUST, JUST, JUST, POOR, POOR, JUST, JUST, NICE, POOR, POOR, JUST},
            {JUST, NICE, JUST, POOR, NICE, JUST, JUST, NICE, JUST, DONT, JUST, POOR, NICE, JUST, JUST},
            {JUST, JUST, JUST, NICE, POOR, JUST, NICE, JUST, JUST, JUST, JUST, NICE, POOR, JUST, JUST},
            {JUST, JUST, JUST, JUST, JUST, JUST, NICE, NICE, JUST, JUST, POOR, JUST, JUST, JUST, JUST},
            {JUST, POOR, JUST, NICE, JUST, JUST, POOR, NICE, JUST, POOR, NICE, JUST, JUST, POOR, JUST},
            {JUST, NICE, JUST, JUST, JUST, NICE, POOR, JUST, POOR, NICE, JUST, NICE, JUST, JUST, JUST},
            {DONT, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, DONT, JUST, JUST, NICE, JUST},
            {JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, JUST, NICE}
    };
}