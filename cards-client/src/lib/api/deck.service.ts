import { getToken } from './auth.service';

export interface Deck {
  id: string;
  name: string;
}

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

export async function getDecks(): Promise<Deck[]> {
  const res = await fetch(`${API_URL}/decks`, {
    method: 'GET',
  });
  if (!res.ok) throw new Error('Failed to fetch decks');
  return res.json();
}

export async function getDeck(deckId: string): Promise<Deck> {
  const res = await fetch(`${API_URL}/decks/${deckId}`, {
    method: 'GET',
  });
  if (!res.ok) throw new Error('Failed to fetch deck');
  return res.json();
}

export async function createDeck(name: string): Promise<Deck> {
  const token = getToken();
  const res = await fetch(`${API_URL}/decks`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
  });
  if (!res.ok) throw new Error('Failed to create deck');
  return res.json();
}

export async function updateDeck(deckId: string, name: string): Promise<Deck> {
  const token = getToken();
  const res = await fetch(`${API_URL}/decks/${deckId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
  });
  if (!res.ok) throw new Error('Failed to update deck');
  return res.json();
}

export async function deleteDeck(deckId: string): Promise<void> {
  const token = getToken();
  const res = await fetch(`${API_URL}/decks/${deckId}`, {
    method: 'DELETE',
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  if (!res.ok) throw new Error('Failed to delete deck');
} 