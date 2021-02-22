# Kotlin-card-game

Here We are playing a game of CHECKMATE for which rules are provided below:

The game is played between a player (or players) and the dealer. The objective of the game is to beat the dealer in one of the following ways:

Get 21 points on the player's first two cards (called a "CheckMate"), without a dealer CheckMate;
Reach a final score higher than the dealer without exceeding 21; or
Let the dealer "draw" additional cards until their hand exceeds 21 (called "busted").

Players are each dealt two cards, face up(exposed). The dealer is also dealt two cards, one up (exposed) and one down (hidden). The value of cards two through ten is their pip value (2 through 10). Face cards (Jack, Queen, and King) are all worth ten. Aces can be worth one or eleven. A hand's value is the sum of the card values. Players are allowed to draw additional cards (hit) to improve their hands. A hand with an ace valued as 11 is called "soft", meaning that the hand will not bust by taking an additional card; the value of the ace will become one to prevent the hand from exceeding 21. Otherwise, the hand is "hard".

Once all the players have completed their hands, it is the dealer’s turn. The dealer hand will not be completed if all players have either busted or received a checkmate. The dealer then reveals the hidden card and must hit until the cards total 17 or more points. A dealer also hits on a "soft" 17, i.e. a hand containing an ace and one or more other cards totaling six. Players win by not busting and having a total higher than the dealer, or not busting and having the dealer bust, or getting a checkmate without the dealer getting a checkmate. If the player and dealer have the same total (not counting checkmate), this is called a "push", and the player typically does not win or lose money on that hand. Otherwise, the dealer wins.
