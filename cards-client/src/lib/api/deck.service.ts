import { withAuth } from './auth.middleware';
import { Deck } from '@/lib/models/Deck.type'

const API_URL = process.env.NEXT_PUBLIC_API_URL ?? 'http://localhost:8080';

const fetchWithAuth = withAuth(fetch);

export async function getDecks(): Promise<Deck[]> {
    const res = await fetchWithAuth(`${API_URL}/decks`, {
        method: 'GET',
    });
    if (!res.ok) throw new Error('Failed to fetchWithAuth decks');
    return res.json();
}

export async function getDeck(deckId: number): Promise<Deck> {
    const res = await fetchWithAuth(`${API_URL}/decks/${deckId}`, {
        method: 'GET',
    });
    if (!res.ok) throw new Error('Failed to fetchWithAuth deck');
    return res.json();
}

export async function createDeck(name: string): Promise<Deck> {;
    const res = await fetchWithAuth(`${API_URL}/decks`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });
    if (!res.ok) throw new Error('Failed to create deck');
    return res.json();
}

export async function updateDeck(deckId: number, name: string): Promise<Deck> {
    const res = await fetchWithAuth(`${API_URL}/decks/${deckId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });
    if (!res.ok) throw new Error('Failed to update deck');
    return res.json();
}

export async function deleteDeck(deckId: number): Promise<void> {
    const res = await fetchWithAuth(`${API_URL}/decks/${deckId}`, {
        method: 'DELETE',
        headers: {
        },
    });
    if (!res.ok) throw new Error('Failed to delete deck');
}
