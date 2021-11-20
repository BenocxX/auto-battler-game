# Event System
Created: 2021-11-20 12:07
<br>

## Important
S'il vous plait, ne pas copier mon code, plutôt vous en inspirer. Merci :)

### À quoi sert un Event System?
Un Event System sert à créer une multitudes d'évènements et de listeners afin de pouvoir gérer des triggers plus facilement et afin de pouvoir "expand" très simplement. L'Event System va gérer quel listener déclancher selon quel évènement est déclanché.

### Programme:
1. Premièrement, on veut se créer une singleton qui va gérer quel listener déclancher selon l'évènement déclanché. Cette classe se nommera "EventSystem". Dans cette classe, on veut contenir une liste des interfaces "listeners" afin de pouvoir les calls lorsqu'un event leur correspondant est déclenché.
	
	Voir: [EventSystem.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/EventSystem.java)
	
2. Ensuite, on veut créer un premier event. Cet event sera un interface se nommant "TriggerAreaListener". Dans cet interface, on peut placer différentes méthodes à appeller selon l'event déclenché.

	Voir: [TriggerAreaListener.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/TriggerAreaListener.java)
	
3. Par la suite, on veut créer un trigger qui va déclencher des events. Notre classe va s'appeller "TriggerArea" et va extends de "StaticEntity". Cette classe va représenter une zone de "déclenchement" dans notre jeu. Lorsque le joueur va y entrer, elle va déclencher un event du EventSystem (voir méthode: triggerCheck(StaticEntity triggerer) sur github). Elle va aussi déclencher un event lorsque le joueur va rester dans la zone de déclenchement et un autre event lorsque le joueur va quitter la zone de déclenchement.

	Voir: [TriggerArea.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/TriggerArea.java)
	
4. Pour finir, on veut créer un objet qui va agir lorsqu'un event est appellé. Cette classe va se nommer "Box", elle va extends "StaticEntity" et va implémenter "TriggerAreaListener". Cette classe va donc avoir les 3 méthodes qu'on a créées dans l'interface "TriggerAreaListener" à l'étape #2. Lorsqu'un event associé au listener qu'implémente cette méthode est déclenché, l'EventSystem va appellé ces méthodes là.

	Voir: [Box.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/Box.java)

### Exemples:
Lorsqu'un item est pick up, le joueur fait une animation de célébration.

onItemPickUp() -> player.celebrate()

Il est impossible de call player.celebrate() dans la classe de l'item lorsqu'il est pick up puisqu'il ne contient pas un référence au player, il faudrait alors passer le player en paramètre et etc... Avec un Event System, la classe Player implements itemEventListener et possède la méthode @override onItemPickUp() dans laquelle il peut effectuer la célébration. La classe Item possède une fonction pickUp() qui va call EventSystem.getInstance().onItemPickUp() qui va ensuite call le "onItemPickUp()" dans le player.

### Sources:
- [How To Build An Event System in Unity](https://www.youtube.com/watch?v=gx0Lt4tCDE0&t=324s)
- [GameDev Architecture - Scriptable Object Event System - Unity - Part 1](https://www.youtube.com/watch?v=iXNwWpG7EhM&t=703s)
- [GameDev Architecture - Scriptable Object Events With Custom Data - Unity - Part 2](https://www.youtube.com/watch?v=P-U7GPXMtLY&t=166s)