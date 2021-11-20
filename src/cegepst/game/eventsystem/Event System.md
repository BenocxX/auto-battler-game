# Event System
Created: 2021-11-20 12:07
<br>

##### À quoi sert un Event System?
Un Event System sert à créer une multitudes d'évènements et de listeners afin de pouvoir gérer des triggers plus facilement et afin de pouvoir "expand" très simplement. L'Event System va gérer quel listener déclancher selon quel évènement est déclanché.

##### Programme:
1. Premièrement, on veut se créer une singleton qui va gérer quel listener déclancher selon l'évènement déclanché. Cette classe se nommera "EventSystem". Dans cette classe, on veut contenir une liste des interfaces "listeners" afin de pouvoir les calls lorsqu'un event leur correspondant est déclenché.
	
	Voir: [EventSystem.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/EventSystem.java)
	
2. Ensuite, on veut créer un premier event. Cet event sera un interface se nommant "TriggerAreaListener". Dans cet interface, on peut placer différentes méthodes à appeller selon l'event déclenché.

	Voir: [TriggerAreaListener.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/TriggerAreaListener.java)
	
3. Par la suite, on veut créer un trigger qui va déclencher des events. Notre classe va s'appeller "TriggerArea" et va extends de "StaticEntity". Cette classe va représenter une zone de "déclenchement" dans notre jeu. Lorsque le joueur va y entrer, elle va déclencher un event du EventSystem (voir méthode: triggerCheck(StaticEntity triggerer) sur github). Elle va aussi déclencher un event lorsque le joueur va rester dans la zone de déclenchement et un autre event lorsque le joueur va quitter la zone de déclenchement.

	Voir: [TriggerArea.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/TriggerArea.java)
	
4. Pour finir, on veut créer un objet qui va agir lorsqu'un event est appellé. Cette classe va se nommer "Box", elle va extends "StaticEntity" et va implémenter "TriggerAreaListener". Cette classe va donc avoir les 3 méthodes qu'on a créées dans l'interface "TriggerAreaListener" à l'étape #2. Lorsqu'un event associé au listener qu'implémente cette méthode est déclenché, l'EventSystem va appellé ces méthodes là.

	Voir: [Box.java sur mon github](https://github.com/BenocxX/auto-battler-game/blob/master/src/cegepst/game/eventsystem/Box.java)