#!/usr/bin/env python3
"""Scenario calculator, not a measurement. Rates checked 2026-11-23, USD."""
import argparse
import json
from decimal import Decimal

parser = argparse.ArgumentParser(description=__doc__)
parser.add_argument('--users', type=int, default=100)
parser.add_argument('--hours-per-day', type=Decimal, default=Decimal('1'))
parser.add_argument('--input-tokens-per-hour', type=int, default=100000,
                    help='All billed context, including repeated history and system instruction')
parser.add_argument('--output-tokens-per-hour', type=int, default=6000)
args = parser.parse_args()
if min(args.users, args.hours_per_day, args.input_tokens_per_hour, args.output_tokens_per_hour) < 0:
    parser.error('Amounts must be nonnegative')
hour = (Decimal(args.input_tokens_per_hour) * Decimal('0.10') +
        Decimal(args.output_tokens_per_hour) * Decimal('0.40')) / 1000000
day = hour * args.users * args.hours_per_day
print(json.dumps({
    'model': 'gemini-2.5-flash-lite',
    'currency': 'USD', 'users': args.users,
    'hours_per_user_daily': str(args.hours_per_day),
    'per_user_hour': str(hour),
    'all_users_daily': str(day),
    'month_30_days': str(day * 30),
    'year_365_days': str(day * 365),
    'excludes': 'tax, hosting, development, user internet; assumes local STT and offline TTS',
}, indent=2))
