package nl.gerardverbeek.util;

public class Options {
    public static final Option MUTATION_RATE = new Option(0.02);


    //Player
    public static final Option PLAYER_OUTPUT_LEFT_SLEEP = new Option(10);
    public static final Option PLAYER_OUTPUT_RIGHT_SLEEP = new Option(10);
    public static final Option PLAYER_OUTPUT_FIRE_SLEEP = new Option(10);

    //Gene
    public static final Option MAX_OUTPUT_SLEEP_TIME = new Option(1500);


    //Population
    public static final Option POPULATION_SIZE = new Option(20);


    //Evolution
    public static final Option GENE_REPLACEMENT_PERCENTAGE = new Option(50);


    //Statistics
    public static final Option STATISTICS_REFRESH_RATE = new Option(2000);

    //Layer
    public static final Option MAX_X_FRAME = new Option(650);
    public static final Option AMOUNT_INPUT_NEURONS = new Option(10);
    public static final Option AMOUNT_HIDDEN_NEURONS = new Option(10);




    //Game
    public static final Option SHOW_GAME_FRAME = new Option(false);
    public static Option GAME_SPEED = new Option(15);

}
