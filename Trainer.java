package Pokemons;

import java.util.Scanner;

public class Trainer
{
    public int level;
    public int EXP;
    public int maxEXP;
    public String name;



    public void Trainer()
    {
        System.out.println("트레이너의 이름을 입력하세요");
        Scanner scanner = new Scanner(System.in);
        name = scanner.next();
        System.out.println("트레이너의 이름은 " + name + "입니다.");

        this.level = 5;
        this.EXP = 0;
        this.maxEXP = 100;
    }

    public void winBattle(WildPokemonBattle)
    {
        int gainedEXP = WildPokemon.getLevel()*30;
        this.EXP += gainedEXP;
        if(this.EXP >= this.maxEXP)
        {
            LevelUp();
        }
    }

    public void winBattle(EnemyTrainerBattle)
    {
        int gainedEXP = EnemyPokemon.getLevel()*30;
        this.EXP += gainedEXP;
        gainedCandy.SetCandy(1);
        if(this.EXP >= this.maxEXP)
        {
            LevelUp();
        }
    }

    public void winBattle()
    {
        int gainedEXP = enemy.getLevel()*30;
        this.EXP += gainedEXP;
        this.gainedCandy = gainedCandy++;
        if(this.EXP >= this.maxEXP)
        {
            LevelUp();
        }
    }

    private void LevelUp()
    {
        this.level++;
        this.maxEXP = this.level*3;
        this.EXP = 0;
    }

    public void loseBattle()
    {
        this.EXP = 0;
    }
}
