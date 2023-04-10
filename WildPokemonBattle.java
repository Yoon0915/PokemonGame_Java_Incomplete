package Pokemons;

public class WildPokemonBattle
{
    void WildBattlePhase()
    {
        BattleModeTrainer obj_1 = new BattleModeTrainer();
        obj_1.BattlePhase();

        WildPokemon obj_2 = new WildPokemon();
        obj_2.playBattle();
    }
}
