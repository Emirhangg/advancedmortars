package com.codetaylor.mc.advancedmortars.modules.mortar.integration.jei;

import com.codetaylor.mc.advancedmortars.lib.gui.GuiHelper;
import com.codetaylor.mc.advancedmortars.modules.mortar.ModuleMortar;
import com.codetaylor.mc.advancedmortars.modules.mortar.recipe.RecipeMortar;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JEIRecipeWrapperMortar
    implements IRecipeWrapper {

  private static final ResourceLocation TEXTURE_SECONDARY_OVERLAY = new ResourceLocation(
      ModuleMortar.MOD_ID,
      "textures/gui/jei_secondary.png"
  );

  private List<List<ItemStack>> inputs;
  private List<ItemStack> outputs;
  private float secondaryOutputChance;

  public JEIRecipeWrapperMortar(RecipeMortar recipe) {

    this.inputs = new ArrayList<>();

    for (Ingredient input : recipe.getIngredients()) {
      this.inputs.add(Arrays.asList(input.getMatchingStacks()));
    }

    this.outputs = new ArrayList<>();

    this.outputs.add(recipe.getOutput());

    if (recipe.getSecondaryOutput() != null) {
      this.outputs.add(recipe.getSecondaryOutput());
    }

    this.secondaryOutputChance = recipe.getSecondaryOutputChance();
  }

  @Override
  public void getIngredients(@Nonnull IIngredients ingredients) {

    ingredients.setInputLists(ItemStack.class, this.inputs);
    ingredients.setOutputs(ItemStack.class, this.outputs);
  }

  public float getSecondaryOutputChance() {

    return this.secondaryOutputChance;
  }

  @Override
  public void drawInfo(
      Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY
  ) {

    if (this.outputs.size() > 1) {
      GuiHelper.drawTexturedRect(minecraft, TEXTURE_SECONDARY_OVERLAY, recipeWidth - 26, 0, 26, 54, 0, 0, 0, 26, 54);


      minecraft.fontRenderer.drawStringWithShadow("100%", recipeWidth - 2, 32, 0xFFFFFF);
    }
  }
}
