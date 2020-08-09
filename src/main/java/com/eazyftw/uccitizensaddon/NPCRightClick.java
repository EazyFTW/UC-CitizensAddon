package com.eazyftw.uccitizensaddon;

import me.TechsCode.UltraCustomizer.*;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;

public class NPCRightClick extends Constructor {

    public NPCRightClick(final UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "NPC Right Click (Citizens)";
    }

    public String getInternalName() {
        return "citizens-right-click";
    }

    public XMaterial getMaterial() {
        return XMaterial.VILLAGER_SPAWN_EGG;
    }

    @Override
    public String getRequiredPlugin() {
        return "Citizens";
    }

    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "right clicks a Citizens NPC." };
    }

    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("id", "Citizen's ID", DataType.STRING, elementInfo), new OutcomingVariable("npcName", "NPC Name", DataType.STRING, elementInfo) };
    }

    @EventHandler
    public void onClick(final NPCRightClickEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                public Object request() {
                    return e.getClicker();
                }
            });
            this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                public Object request() {
                    return String.valueOf(e.getNPC().getId());
                }
            });
            this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                public Object request() {
                    return e.getNPC().getName();
                }
            });
            return instance;
        });
    }

    public boolean isUnlisted() {
        return false;
    }
}
