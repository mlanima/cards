import { withAuth } from './auth.middleware';

export interface Card {
    id: number;
    phrase: string;
    translation: string;
}


const fetchWithAuth = withAuth(fetch);

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

export async function getCards(deckId: number): Promise<Card[]> {
    const res = await fetchWithAuth(`${API_URL}/decks/${deckId}/cards`, {
        method: 'GET',
        headers: {
        },
    });
    if (!res.ok) throw new Error('Failed to fetchWithAuth cards');
    return res.json();
}

export async function addCard(
    deckId: number,
    phrase: string,
    translation: string
): Promise<Card> {
    const res = await fetchWithAuth(`${API_URL}/decks/${deckId}/cards`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ phrase, translation }),
    });
    if (!res.ok) throw new Error('Failed to add card');
    return res.json();
}
