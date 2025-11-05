-- Demon Slayer (Kimetsu no Yaiba) Database Schema for PostgreSQL (Neon)
-- Filename: demon_slayer_schema.sql
-- Purpose: Comprehensive relational schema to model characters, demons, breathing styles,
-- techniques, swords, missions, battles, locations, episodes/arcs, and relationships.

-- Extensions & Settings
CREATE EXTENSION IF NOT EXISTS pgcrypto; -- for gen_random_uuid()
SET search_path = public;

-- Enumerations
CREATE TYPE role_enum AS ENUM ('demon_slayer','hashira','pupil','swordsmith','medicine','inhabitant','enemy','other');
CREATE TYPE rank_enum AS ENUM ('unranked','lower_rank','upper_rank','hashira');
CREATE TYPE demon_rank_enum AS ENUM ('lower_moon','upper_moon','common','kizuki','demon_lord');
CREATE TYPE sword_type_enum AS ENUM ('nichirin','wooden','replica','other');
CREATE TYPE mission_status_enum AS ENUM ('planned','active','completed','failed');

-- Core Tables
-- Characters (Demon Slayers and other humans)
CREATE TABLE characters (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    japanese_name TEXT,
    alias TEXT,
    birth_date DATE,
    gender TEXT,
    role role_enum DEFAULT 'demon_slayer',
    rank rank_enum DEFAULT 'unranked',
    height_cm INTEGER,
    weight_kg INTEGER,
    birthplace TEXT,
    bio TEXT,
    created_at timestamptz DEFAULT now(),
    updated_at timestamptz DEFAULT now()
);

-- Demons
CREATE TABLE demons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    japanese_name TEXT,
    alias TEXT,
    origin TEXT,
    demon_rank demon_rank_enum DEFAULT 'common',
    blood_power INTEGER, -- arbitrary power metric
    weaknesses TEXT,
    bio TEXT,
    first_appearance TEXT,
    created_at timestamptz DEFAULT now(),
    updated_at timestamptz DEFAULT now()
);

-- Breathing Styles (Water, Thunder, Beast, etc.)
CREATE TABLE breathing_styles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL UNIQUE,
    origin TEXT,
    description TEXT
);

-- Techniques (Forms/Techniques tied to breathing styles)
CREATE TABLE techniques (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    breathing_style_id UUID REFERENCES breathing_styles(id) ON DELETE CASCADE,
    name TEXT NOT NULL,
    form_number INTEGER,
    description TEXT,
    difficulty_level INTEGER,
    UNIQUE (breathing_style_id, name)
);

-- Characters <-> Breathing Styles (many-to-many)
CREATE TABLE character_breathing_styles (
    character_id UUID REFERENCES characters(id) ON DELETE CASCADE,
    breathing_style_id UUID REFERENCES breathing_styles(id) ON DELETE CASCADE,
    primary_style BOOLEAN DEFAULT FALSE,
    notes TEXT,
    PRIMARY KEY (character_id, breathing_style_id)
);

-- Indexes & Performance
CREATE INDEX IF NOT EXISTS idx_characters_name ON characters (lower(name));
CREATE INDEX IF NOT EXISTS idx_demons_name ON demons (lower(name));
CREATE INDEX IF NOT EXISTS idx_techniques_name ON techniques (lower(name));
CREATE INDEX IF NOT EXISTS idx_breathing_styles_name ON breathing_styles (lower(name));

-- Triggers (auto-update updated_at)
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_characters_updated_at
    BEFORE UPDATE ON characters
    FOR EACH ROW EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TRIGGER trg_demons_updated_at
    BEFORE UPDATE ON demons
    FOR EACH ROW EXECUTE PROCEDURE trigger_set_timestamp();

-- Sample Data
-- Breathing styles
INSERT INTO breathing_styles (id, name, origin, description)
VALUES
    (gen_random_uuid(), 'Water Breathing', 'Hashira tradition', 'Techniques using flowing water-like motions.'),
    (gen_random_uuid(), 'Thunder Breathing', 'Mastered by few', 'Fast, lightning-based strikes.'),
    (gen_random_uuid(), 'Beast Breathing', 'Derived from personal training', 'Wild, animalistic fighting style.'),
    (gen_random_uuid(), 'Flower Breathing', 'Medical & precise', 'Elegant and precise forms often used by insect-style users.');

-- Characters (examples)
INSERT INTO characters (id, name, japanese_name, alias, birth_date, gender, role, rank, height_cm, weight_kg, birthplace, bio)
VALUES
    (gen_random_uuid(), 'Tanjiro Kamado', '竈門 炭治郎', 'Tanjiro', '2000-07-14', 'male', 'demon_slayer', 'unranked', 165, 61, 'Final Valley', 'Kind-hearted Demon Slayer seeking a cure for his sister.'),
    (gen_random_uuid(), 'Nezuko Kamado', '竈門 禰豆子', 'Nezuko', NULL, 'female', 'other', 'unranked', 153, 45, 'Final Valley', 'Younger sister turned demon but retains human emotions.'),
    (gen_random_uuid(), 'Zenitsu Agatsuma', '我妻 善逸', NULL, NULL, 'male', 'demon_slayer', 'unranked', 164, 58, 'Unknown', 'Fearful but powerful when unconscious.'),
    (gen_random_uuid(), 'Inosuke Hashibira', '嘴平 伊之助', 'Inosuke', NULL, 'male', 'demon_slayer', 'unranked', 165, 60, 'Mountain', 'Raised by boars and fights with Beast Breathing.');

-- Example demons
INSERT INTO demons (id, name, alias, demon_rank, blood_power, weaknesses, bio, first_appearance)
VALUES
    (gen_random_uuid(), 'Lower Moon Demon Example', 'Lower Moon', 'lower_moon', 40, 'Sunlight, Nichirin blade', 'A lower-ranked demon encountered by slayers.', 'Arc 1');

-- Example techniques
INSERT INTO techniques (id, breathing_style_id, name, form_number, description, difficulty_level)
SELECT gen_random_uuid(), bs.id, 'First Form: Water Surface Slash', 1, 'A fundamental water breathing horizontal slash.', 1
FROM breathing_styles bs WHERE bs.name = 'Water Breathing' LIMIT 1;