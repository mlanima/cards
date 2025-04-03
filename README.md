# Cards-Based Dictionary API

## Overview
This is a Java Spring Boot API for creating card-based dictionaries (decks) to help users learn languages efficiently. The API supports user authentication, deck management, AI-generated cards, and group-based learning.

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
- `POST /group` - Create a new group
- `GET /group` - Retrieve all groups
- `GET /group/{groupId}/decks` - Retrieve decks associated with a group

### AI-Powered Cards
- `PUT /ai/decks/cards` - Generate cards using AI

## Authorization
Most endpoints require authentication via Bearer Token.

