import { getToken } from './auth.service';
import type { Card } from './card.service';

const API_URL = process.env.NEXT_PUBLIC_API_URL ?? 'http://localhost:8080';

export async function generateAICards(
    deckId: string,
    prompt: string
): Promise<Card[]> {
    const token = getToken();
    const res = await fetch(`${API_URL}/ai/decks/cards`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ deckId, prompt }),
    });
    if (!res.ok) throw new Error('Failed to generate AI cards');
    return res.json();
}
