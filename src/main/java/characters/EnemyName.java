package characters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnemyName {
    WARG("Warg"),
    GHOUL("Ghoul"),
    DEATHKNIGHT("Death Knight"),
    DREADLORD("Dreadlord"),
    SHADE("Shade"),
    IMP("Imp"),
    WITCH("Witch"),
    SIREN("Siren"),
    WYVERN("Wyvern"),
    REVENANT("Revenant"),
    WRAITH("Wraith"),
    BANSHEE("Banshee"),
    SHADOWMENDER("Shadowmender"),
    NIGHTMARE("Nightmare"),
    GARGOYLE("Gargoyle"),
    DARKMAGE("Dark Mage"),
    SKELETON("Skeleton"),
    ZOMBIE("Zombie"),
    PHANTOM("Phantom"),
    SPECTER("Specter"),
    LICH("Lich"),
    DRAGON("Dragon"),
    SERPENT("Serpent"),
    CERBERUS("Cerberus"),
    FALLEN_ANGEL("Fallen Angel"),
    SHADOWLORD("Shadowlord"),
    DIREWOLF("Direwolf"),
    VAMPIRE("Vampire"),
    ORC("Orc"),
    GOLEM("Golem"),
    CHIMERA("Chimera"),
    MINOTAUR("Minotaur"),
    HARPY("Harpy"),
    NAGA("Naga"),
    DEMON("Demon"),
    TROLL("Troll"),
    HYDRA("Hydra"),
    GRIFFIN("Griffin"),
    PHOENIX("Phoenix"),
    DOPPELGANGER("Doppelganger"),
    BEHEMOTH("Behemoth"),
    MANTICORE("Manticore"),
    CRIMSON_KNIGHT("Crimson Knight"),
    RAVAGER("Ravager"),
    CTHULHU("Cthulhu"),
    KRAKEN("Kraken"),
    ABYSSAL_LEVIATHAN("Abyssal Leviathan"),
    NIGHTSHADE("Nightshade"),
    WITCHDOCTOR("Witchdoctor"),
    WEREWOLF("Werewolf"),
    WARLOCK("Warlock"),
    SHADOWHOUND("Shadowhound"),
    SPECTRAL_KNIGHT("Spectral Knight");
    private final String name;
    private static final List<EnemyName> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    EnemyName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    public static EnemyName randomEnemyName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
