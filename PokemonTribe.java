import java.util.Vector;
import java.util.List;
import java.util.Arrays;

public class PokemonTribe {
    public static final int NUMBER_OF_POKEMON = 14;

    public static enum ExpFormulaIndex {
        FAST, MDFA, MDSL, SLOW;
    }

    public class Evo {
        public int evoLevel;
        public int evoPokeIndex;
    }

    public class TribeStatus {
        public int health; // 체력
        public int attack; // 공격
        public int block; // 방어
        public int contact; // 특공
        public int defense; // 특방
        public int speed; // 속도

        public TribeStatus(int health, int attack, int block, int contact, int defense, int speed) {
        }

        public String toString() {
            return health + " " + attack + " " + block + " " + contact + " " + defense + " " + speed + " ";
        }
    }

    public int m_dicNum; // 도감 번호
    public List<PokemonType> m_types; // 갖고 있는 타입(들)
    public String m_name; // 종족 이름 ex) 이상해씨
    public TribeValue m_tribeValue; // 종족치 HABCDS
    public Evo m_evo; // 진화를 할 레벨 + 진화될 포켓몬의 도감 번호
    public ExpFormulaIndex m_expFormulaIndex; // '레벨에 따른 필요 경험치 공식'에 대한 인덱스
    public int m_baseExpYield; // 종족별 패배하였을 때 상대에게 주는 경험치량
    public int m_catchRate; // 종족별 포획률


    public PokemonTribe(int dicNum, List<PokemonType> types, String name, TribeValue tribeValue, Evo evo,
                        ExpFormulaIndex expFormulaIndex, int baseExpYield, int catchRate) {
        m_dicNum = dicNum;
        m_types = new Vector<PokemonType>(types);
        m_name = name;
        m_tribeValue = tribeValue;
        m_evo = evo;
        m_expFormulaIndex = expFormulaIndex;
        m_baseExpYield = baseExpYield;
        m_catchRate = catchRate;
    }

    public int MaxExpByLevel(int level) {
        int powN3 = level * level * level;
        switch (m_expFormulaIndex) {
            case FAST:
                return 4 * powN3 / 5;
            case MDFA:
                return powN3;
            case MDSL:
                return 6 * powN3 / 5 - 15 * level * level + 100 * level - 140;
            case SLOW:
                return 5 * powN3 / 4;
            default:
                return 0;
        }
    }

    public class PokemonIndex {
        public int m_dicNum;
        public List<PokemonType> m_types;d
        public String m_name;
        public TribeValue m_tribeValue;
        public Evo m_evo;
        public ExpFormulaIndex m_expFormulaIndex;
        public int m_baseExpYield;
        public int m_catchRate;

        public PokemonTribe(int dicNum, List<PokemonType> types, String name, TribeValue tribeValue, Evo evo,
                            ExpFormulaIndex expFormulaIndex, int baseExpYield, int catchRate) {
            m_dicNum = dicNum;
            m_types = types;
            m_name = name;
            m_tribeValue = tribeValue;
            m_evo = evo;
            m_expFormulaIndex = expFormulaIndex;
            m_baseExpYield = baseExpYield;
            m_catchRate = catchRate;
        }

        public int MaxExpByLevel(int level) {
            int powN3 = level * level * level;
            switch (m_expFormulaIndex) {
                case FAST:
                    return 4 * powN3 / 5;
                case MDFA:
                    return powN3;
                case MDSL:
                    return 6 * powN3 / 5 - 15 * level * level + 100 * level - 140;
                case SLOW:
                    return 5 * powN3 / 4;
                default:
                    return 0;
            }
        }

        public TribeValue StatsByLevel(int level) {
            int health = (2 * m_tribeValue.health + 100) * level / 100 + 10;
            int attack = (2 * m_tribeValue.attack + 31) * level / 100 + 5;
            int block = (2 * m_tribeValue.block + 31) * level / 100 + 5;
            int contact = (2 * m_tribeValue.contact + 31) * level / 100 + 5;
            int defense = (2 * m_tribeValue.defense + 31) * level / 100 + 5;
            int speed = (2 * m_tribeValue.speed + 31) * level / 100 + 5;

            return new TribeValue(health, attack, block, contact, defense, speed);
        }

        public Evo GetEvoValue() {
            return m_evo;
        }

        public void Evolution(List<PokemonTribe> pokemonDic) {
            int index = m_evo.evoPokeIndex;
            PokemonTribe nextForm = pokemonDic.get(index);
            this.m_dicNum = nextForm.m_dicNum;
            this.m_types = new Vector<>(nextForm.m_types);
            this.m_name = nextForm.m_name;
            this.m_tribeValue = nextForm.m_tribeValue;
            this.m_evo = nextForm.m_evo;
            this.m_expFormulaIndex = nextForm.m_expFormulaIndex;
            this.m_baseExpYield = nextForm.m_baseExpYield;
            this.m_catchRate = nextForm.m_catchRate;
        }

        public boolean IsLastEvo() {
            return m_evo.evoLevel == -1;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(m_dicNum).append(' ').append(m_name).append(' ');
            for (PokemonType type : m_types) {
                sb.append(stringType[type]).append(' ');
            }
            return sb.toString();
        }
    }

    enum PokemonDicNum {
        김뮤진,
        이상해씨,
        이상해풀,
        이상해꽃,
        파이리,
        리자드,
        리자몽,
        꼬부기,
        어니부기,
        거북왕,
        캐터피,
        단데기,
        버터플,
        구구,
        피죤,
        피죤투,
        피카츄,
        라이츄
    }

    List<PokemonTribe> pokemonDic = Arrays.asList(
            new PokemonTribe(김뮤진, new Type[]{PSYSHC}, "김뮤진", new int[]{999, 999, 999, 999, 999, 999}, new int[]{-1, -1}, FAST, 999, 255),
            new PokemonTribe(이상해씨, new Type[]{GRASS, POISON}, "이상해씨", new int[]{45, 49, 49, 65, 65, 45}, new int[]{16, 이상해풀}, MDSL, 64, 45),
            new PokemonTribe(이상해풀, new Type[]{GRASS, POISON}, "이상해풀", new int[]{60, 62, 63, 80, 80, 60}, new int[]{32, 이상해꽃}, MDSL, 141, 45),
            new PokemonTribe(이상해꽃, new Type[]{GRASS, POISON}, "이상해꽃", new int[]{80, 82, 83, 100, 100, 80}, new int[]{-1, -1}, MDSL, 208, 45),
            new PokemonTribe(파이리, new Type[]{FIRE}, "파이리", new int[]{39, 52, 43, 60, 50, 65}, new int[]{16, 리자드}, MDSL, 65, 45),
            new PokemonTribe(리자드, new Type[]{FIRE}, "리자드", new int[]{58, 64, 58, 80, 65, 80}, new int[]{36, 리자몽}, MDSL, 142, 45),
            new PokemonTribe(리자몽, new Type[]{FIRE, FLYING}, "리자몽", new int[]{78, 84, 78, 109, 85, 100}, new int[]{-1, -1}, MDSL, 209, 45),
            new PokemonTribe(꼬부기, new Type[]{WATER}, "꼬부기", new int[]{44, 48, 65, 50, 64, 43}, new int[]{16, 어니부기}, MDSL, 66, 45),
            new PokemonTribe(어니부기, new Type[]{WATER}, "어니부기", new int[]{59, 63, 80, 65, 80, 58}, new int[]{36, 거북왕}, MDSL, 143, 45),
            new PokemonTribe(거북왕, new Type[]{WATER}, "거북왕", new int[]{79, 83,100, 85,105, 78}, new int[]{-1,        -1 },MDSL,210, 45),
            new PokemonTribe(캐터피, new Type[]{BUG}, "캐터피", new int[]{45, 30, 35, 20, 20, 45}, new int[]{  7,    단데기 },MDSL,53, 255),
            new PokemonTribe(단데기, new Type[]{BUG}, "캐터피", new int[]{ 50, 20, 55, 25, 25, 30}, new int[]{ 10,    버터플 },MDSL,72, 120),
            new PokemonTribe(버터플, new Type[]{ BUG,FLYING}, "버터플", new int[]{60, 45, 50, 80, 80, 70}, new int[]{ -1,        -1 },MDSL,160, 45),
            new PokemonTribe(구구, new Type[]{ NORMAL,FLYING}, "구구", new int[]{40, 45, 40, 35, 35, 56}, new int[]{18,      피죤 },MDSL,55, 255),
            new PokemonTribe(피죤, new Type[]{ NORMAL,FLYING}, "피죤", new int[]{63, 60, 55, 50, 50, 71}, new int[]{18,      피죤 },MDSL,55, 255),
            new PokemonTribe(피죤투, new Type[]{ NORMAL,FLYING}, "피죤투", new int[]{83, 80, 75, 70, 70,101}, new int[]{-1,        -1 },MDSL,172, 45),
            new PokemonTribe(피카츄, new Type[]{ ELECTR}, "피카츄", new int[]{35, 55, 30, 50, 50, 90}, new int[]{ 36,    라이츄 },MDSL,82, 190),

            new PokemonTribe(라이츄, new Type[]{ ELECTR}, "라이츄", new int[]{60, 90, 55, 90, 80,100}, new int[]{-1,        -1 },MDSL,122, 75));
}
