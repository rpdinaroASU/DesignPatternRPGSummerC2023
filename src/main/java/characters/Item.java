package characters;

import java.util.ArrayList;
import java.util.List;

public enum Item {
    SHARP_STICK("Sharp Stick", 1, 0, 0, 0, 1, 1),
    WOODEN_SWORD("Wooden Sword", 2, 0, 1, 0, 10, 2),
    IRON_DAGGER("Iron Dagger", 3, 0, 1, 0, 20, 3),
    STEEL_MACE("Steel Mace", 5, 0, 1, 0, 40, 5),
    GOLDEN_AXE("Golden Axe", 7, 0, 2, 0, 70, 7),
    DIAMOND_AXE("Diamond Axe", 10, 0, 2, 0, 150, 10),
    ENCHANTED_BOW("Enchanted Bow", 12, 0, 3, 0, 200, 12),
    RUNE_SWORD("Rune Sword", 14, 0, 4, 0, 250, 14),
    DRAGON_SCIMITAR("Dragon Scimitar", 16, 0, 5, 0, 300, 16),
    GLACIAL_LANCE("Glacial Lance", 18, 0, 6, 1, 350, 18),
    MASTERS_BLADE("Master's Blade", 20, 0, 7, 1, 400, 20),
    THUNDER_HAMMER("Thunder Hammer", 22, 0, 8, 2, 450, 22),
    FIRE_LONGSWORD("Fire Longsword", 24, 0, 9, 2, 500, 24),
    ETHEREAL_SPEAR("Ethereal Spear", 26, 1, 10, 3, 550, 26),
    WIND_GLAIVE("Wind Glaive", 28, 1, 11, 3, 600, 28),
    VOID_VORTEX_MACE("Void Vortex Mace", 30, 2, 12, 4, 650, 30),
    ASTRA_SCYTHE("Astra Scythe", 32, 2, 13, 4, 700, 32),
    COSMIC_CLEAVER("Cosmic Cleaver", 34, 3, 14, 5, 750, 34),
    DEATHS_SCYTHE("Death's Scythe", 36, 10, 15, 5, 800, 36),
    INFINITY_GAUNTLET("Infinity Gauntlet", 40, 30, 16, 8, 10000, 40),
    BURNED_BOOK("Burned Book", 0, 1, 0, 0, 1, 1),
    NOVICE_SCROLL("Novice Scroll", 0, 2, 0, 0, 10, 2),
    LEATHER_BOUND_TOME("Leather Bound Tome", 0, 3, 0, 0, 20, 3),
    ENCHANTED_PARCHMENT("Enchanted Parchment", 0, 5, 0, 0, 40, 5),
    SILVER_INLAID_SCROLL("Silver Inlaid Scroll", 0, 7, 0, 0, 70, 7),
    TOTEM_OF_INTELLIGENCE("Totem of Intelligence", 0, 10, 0, 0, 150, 10),
    GLOWING_MANUSCRIPT("Glowing Manuscript", 0, 12, 1, 0, 200, 12),
    MYSTIC_TABLET("Mystic Tablet", 0, 14, 1, 0, 250, 14),
    ORNATE_GLYPH("Ornate Glyph", 0, 16, 2, 0, 300, 16),
    ETHEREAL_INCANTATION("Ethereal Incantation", 0, 18, 3, 0, 350, 18),
    HITCHHIKERS_GUIDE_TO_THE_AFTERLIFE("Hitchhiker's Guide to the Afterlife", 0, 20, 4, 0, 400, 20),
    ARCANE_CHRONICLE("Arcane Chronicle", 0, 22, 5, 1, 450, 22),
    INFUSED_PAPYRUS("Infused Papyrus", 0, 24, 6, 2, 500, 24),
    SPELLBOUND_VELLUM("Spellbound Vellum", 0, 26, 7, 3, 550, 26),
    ETHEREAL_TABLET("Ethereal Tablet", 0, 28, 8, 4, 600, 28),
    COSMIC_COMPENDIUM("Cosmic Compendium", 0, 30, 9, 5, 650, 30),
    ANCIENT_GRIMOIRE("Ancient Grimoire", 0, 32, 10, 6, 700, 32),
    STAFF_OF_WISDOM("Staff of Wisdom", 0, 34, 11, 7, 750, 34),
    STAFF_OF_LIFE("Staff of Life", 0, 40, 13, 10, 10000, 40),
    WOVEN_CLOAK("Woven Cloak", 0, 0, 1, 0, 5, 1),
    LEATHER_TUNIC("Leather Tunic", 0, 0, 2, 0, 10, 2),
    HIDE_ARMOR("Hide Armor", 0, 0, 3, 0, 20, 3),
    CHAIN_MAIL("Chain Mail", 0, 0, 5, 0, 40, 5),
    STEEL_PLATE("Steel Plate", 0, 0, 7, 0, 70, 7),
    REINFORCED_BREASTPLATE("Reinforced Breastplate", 0, 0, 10, 1, 150, 10),
    ENCHANTED_VEST("Enchanted Vest", 0, 10, 12, 2, 200, 12),
    MITHRIL_CUIRASS("Mithril Cuirass", 0, 0, 14, 3, 250, 14),
    DRAGONSCALE_ARMOR("Dragonscale Armor", 0, 0, 16, 4, 300, 16),
    SHADOW_MAIL("Shadow Mail", 0, 0, 18, 5, 350, 18),
    ETHEREAL_ROBE("Ethereal Robe", 0, 0, 20, 6, 400, 20),
    ARCANE_PLATE("Arcane Plate", 0, 0, 22, 7, 450, 22),
    INFUSED_CUIRASS("Infused Cuirass", 0, 0, 24, 8, 500, 24),
    SPELLBOUND_VESTMENT("Spellbound Vestment", 0, 0, 26, 9, 550, 26),
    COSMIC_CUIRASS("Cosmic Cuirass", 0, 0, 28, 10, 600, 28),
    ANCIENT_ARMOR("Ancient Armor", 0, 0, 30, 11, 700, 32),
    ENLIGHTENED_ROBE("Enlightened Robe", 0, 0, 32, 12, 750, 34),
    ETERNAL_MAIL("Eternal Mail", 0, 0, 34, 13, 800, 36),
    INFINITY_ARMOR("Infinity Armor", 10, 10, 40, 15, 10000, 40);
    private final String name;
    private final int damage;
    private final int intelligence;
    private final int defence;
    private final int resist;
    private final int value;
    private final int levelAppears;

    /**
     * Represent the Item.
     * @param name name of item
     * @param damage physical damage dealt
     * @param intelligence magic damage dealt
     * @param defence physical resistance given
     * @param resist magical resistance given
     * @param value gold value of item
     * @param levelAppears the level this item becomes available
     */
    Item(String name, int damage, int intelligence, int defence, int resist, int value, int levelAppears) {
        this.name = name;
        this.damage = damage;
        this.intelligence = intelligence;
        this.defence = defence;
        this.resist = resist;
        this.value = value;
        this.levelAppears = levelAppears;
    }

    /**
     * Returns the damage dealt.
     * @return the damage dealt
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the defence granted by an item.
     * @return the defence granted by an item
     */
    public int getDefence() {
        return defence;
    }

    /**
     * Returns the resistance given by an item.
     * @return the resistance given by an item
     */
    public int getResist() {
        return resist;
    }

    /**
     * Returns the value of an Item.
     * @return the value of an Item
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the magical damage increased.
     * @return the magical damage increased
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Returns the level the item appears on.
     * @return the level the item appears on
     */
    public int getLevelAppears() {
        return levelAppears;
    }

    /**
     * Returns the name of the item.
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Return a list of items available to the character.
     * @param characterLevel the level of the character
     * @return the list of items available to the character
     */
    public static List<Item> getItemPool(double characterLevel) {
        List<Item> itemPool = new ArrayList<>();
        for (Item item : Item.values()) {
            if (item.getLevelAppears() <= characterLevel) {
                itemPool.add(item);
            }
        }
        return itemPool;
    }
}
