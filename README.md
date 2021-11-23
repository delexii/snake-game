# Tiger Cubed Games

Makers Final Group Project by:
- Leo Brown 
- Delia Bute
- Daniel Szabo
- Maisha Chowdhury


### Project planning
- [x] communicate individual goals for the last project
- [x] [brainstorm](https://docs.google.com/document/d/1AJqtVvRKy-G0bmuS2OkYHLRLc4piygYUz-p7nggh9cE/edit#) a feasible game idea
- [x] decide the core MVP features of the project
- [x] agree on team schedule and [ways of working](https://trello.com/b/ILAFejyB/final-project)
- [x] plan the first sprint and identify key tickets
- [x] research and decide the relevant java game engine to be used

The desktop game uses:

- `libGDX` game engine to create the app
- `gradle` to build the project

### QuickStart Instructions

- Fork and clone this repository to your machine
- Open the codebase in an IDE like InteliJ or Android Studio
- Install Java Runtime Environment (JRE) on your local machine

### User Stories
```
As a player,
So that I can start the snake game,
I would like to be able to see a main game screen.
```

```
As a player,
So that I can play the snake game,
I would like to be able to see an object rendered on the screen.
```

```
As a player,
So that I can play the snake game,
I would like to be able to move the object up, down, left and right on the screen.
```

```
As a player,
So that I can end the snake game,
I would like the game to quit when the object crosses the screen edges .
```

```
As a player,
So that I know the game is over,
I would like to be able to see an endgame screen.
```

```
As a player,
So that I can attempt to replay the game again,
I would like to be able to see a replay button on the endgame screen.
```

```
As a player,
So that I can grow the body of my snake,
I would like to be able to see an apple rendered on the screen.
```

```
As a player,
So that I can decrease the body of my snake,
I would like to be able to see a rotten apple rendered on the screen.
```

```
As a player,
So that I can play the game for longer,
I would like to be able to continue playing once the snake head crosses its own body and loses a few body parts.
```

```
As a player,
So that the game does not come to an end too soon,
I would like to not be able to move the snake backwards on itself.
```

### Project features

Core MVP

- [x] A user can see a game screen as a background
- [x] A user can see an object on the screen
- [x] A user can move the object by pressing `up`, `down`, `left` or `right`
- [x] A user can end the game by directing the object towards the edges of the screen
- [x] [MVP Demo](https://github.com/delexii/snake-game/blob/5f91c9a7e953d3cf1fcdc2eda962336d40780458/core/assets/MVP%20Demo.mov)

Extended MVP
- [x] A user can see a welcome screen with two options
- [x] A user can see a title on the welcome screen
- [x] A user can click a `new game` button and be redirected to the main game screen
- [x] A user can move the snake in any direction but backwards on itself
- [x] A user can direct the snake to a good apple object and grow the snake's tail by one body segment
- [x] A user can direct the snake to a rotten apple object and decrease the snake's tail by one body segment
- [x] A user can lose the game if the body of the snake gets shortened to head and tail only
- [x] A user can make the snake cross its own tail and lose segments of the body  
- [x] A user can see an endgame screen once the snake touches the edges of the screen
- [x] A user can click `try again` to be redirected to the main game screen
- [x] A user can click `exit` to close the game window



