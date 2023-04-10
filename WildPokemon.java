package Pokemons;
import java.util.Random;


    public class WildPokemon
    {
        private String wildPokemonName;
        private int wildPokemonLevel;
        private int wildPokemonHP;
        private int maxHP;
        private boolean isMyTurn;

        public WildPokemon()
        {
            // IndexArray에서 랜덤한 포켓몬 정보를 선택
            Random random = new Random();
            int i = random.nextInt(10);
            Pokemon wildPokemon = indexArray[i];

            // 선택한 포켓몬 정보로 배틀 정보 초기화
            wildPokemonName = PokemonTribe.getName();
            wildPokemonHP = PokemonTribe.getHP();
            maxHP = PokemonTribe.getMaxHP();
            wildPokemonLevel = Trainer.getLevel() + random.nextInt(hj5) - 2;
            isMyTurn = true;

        }

        void playBattle()
        {
            System.out.println("야생 포켓몬: " + wildPokemonName + " Lv: " + wildPokemonLevel +
                    " HP: " + wildPokemonHP + "/" + maxHP);
            while (wildPokemonHP > 0)
            {
                if (isMyTurn)
                {
                    System.out.println("상대 포켓몬을 공격합니다.");
                    int damage = Attack.calculateDamage();
                    wildPokemonHP -= damage;
                    System.out.println("상대 포켓몬에게 " + damage + "의 데미지를 입혔습니다.");
                    System.out.println("턴을 종료합니다.");
                }
                else
                {
                    System.out.println("상대 포켓몬이 공격합니다.");
                    int damage = wildPokemonLevel * (new Random().nextInt(11) + 5);
                    Trainer.getMyPokemon().reduceHP(damage);
                    System.out.println("내 포켓몬이 " + damage + "의 데미지를 입었습니다.");
                    System.out.println("턴을 종료합니다.");
                }
                isMyTurn = !isMyTurn;
            }
        }
    }
