Core plugin for the Honeywood Server

Currently added:
  
  1/1000 chance to obtain "Pollen" from right clicking or killing a bee, the chance can be edited in the config.yml file.
  
  Honeywoodreload command to reload the config.yml file so the pollen_chance is always correct.
  
  givenecklace command will give the player who executed the command all of the current necklaces made, only if the player has the "honeywoodcore.givenecklace" permission.
  
  givearmband command will give the player who executed the command all of the current armbands made, only if the player has the "honeywoodcore.givearmband" permission.
  
  giveop command will give the player "Chef____" or "CleoTheKitteh" the operator permission if either of the two executes the command, if another player not specified executes the command it will send a "You do not have permission to execute this command." message.
  
  toggleclicklimit toggles the ability to bypass the click speed check, when disabled the "§6§lHoneywood §r§7» §cSlow down your clicking!" doesn't get sent and the log for console doesn't send the message.
  
  The click limit checks if the player has clicked a bee 12 times a second, if true then it sends a message to the player and cancels the click event, it will also log the players name in the console with their current click count.
  
TODO:
  
  Make a GUI shop that houses the Arm bands and Necklaces so players can purchase them for the item currency "Pollen".
  
  
