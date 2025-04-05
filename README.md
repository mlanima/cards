# Cards-Based Dictionary API

## Overview
This is a Java Spring Boot API for creating card-based dictionaries (decks) to help users learn languages efficiently. The API supports user authentication, deck management, AI-generated cards.

## Features
- User authentication and token management
- CRUD operations for decks and cards
- Group management
- AI-generated cards for enhanced learning

## API Endpoints

### Authentication
- `POST /auth/users` - Register a new user
- `POST /auth/token` - Authenticate user and get a token
- `GET /auth/token` - Refresh authentication token

### Decks and Cards
- `POST /decks` - Create a new deck
- `GET /decks` - Retrieve all decks
- `GET /decks/{deckId}` - Retrieve a specific deck
- `PUT /decks/{deckId}` - Update deck information
- `DELETE /decks/{deckId}` - Delete a deck

### Cards within Decks
- `POST /decks/{deckId}/cards` - Add a card to a deck
- `GET /decks/{deckId}/cards` - Get all cards in a deck
- `GET /decks/{deckId}/cards/{cardId}` - Get a specific card
- `PUT /decks/{deckId}/cards/{cardId}` - Update a card
- `DELETE /decks/{deckId}/cards/{cardId}` - Remove a card from a deck

### Groups
- `POST /groups` - Create a new group
- `GET /groups` - Retrieve all groups
- `GET /groups/{groupId}` - Retrieve group with Id
- `PUT /groups/{groupId}` - Update group name
- `DELETE /groups/{groupId}` - Delete group with Id

### Realtive routes to decks in group
- `GET /groups/{groupId}/decks` - Retrieve decks associated with a group
- `POST /groups/{groupId}/decks` - Create a new deck in group
- `GET /groups/{groupId}/decks/{deckId}` - Retrieve a specific deck in group
- `PUT /groups/{groupId}/decks/{deckId}` - Update deck information in group
- `DELETE /groups/{groupId}/decks/{deckId}` - Delete a deck in group

### AI-Powered Cards
- `PUT /ai/decks/cards` - Generate cards using AI

## Authorization
Most endpoints require authentication via Bearer Token.

