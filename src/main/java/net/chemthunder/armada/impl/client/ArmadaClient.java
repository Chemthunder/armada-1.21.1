package net.chemthunder.armada.impl.client;

import net.chemthunder.armada.impl.index.ArmadaEntities;
import net.fabricmc.api.ClientModInitializer;

public class ArmadaClient implements ClientModInitializer {
    public void onInitializeClient() {
        ArmadaEntities.clientInit();
    }
}
