package com.example.weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import com.example.weatherapp.data.model.Season

@Composable
fun SeasonIllustration(season: Season, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        when (season) {
            Season.Summer -> drawSummer()
            Season.Rainy -> drawRainy()
            Season.Autumn -> drawAutumn()
            Season.Winter -> drawWinter()
        }
    }
}

private fun DrawScope.drawSummer() {
    val width = size.width
    val height = size.height

    // Far background mountains (Very sharp peaks, varying blue-gray shades)
    drawMountain(
        path = Path().apply {
            moveTo(0f, height * 0.75f)
            lineTo(width * 0.15f, height * 0.55f)
            lineTo(width * 0.28f, height * 0.65f)
            lineTo(width * 0.42f, height * 0.45f)
            lineTo(width * 0.55f, height * 0.6f)
            lineTo(width * 0.7f, height * 0.35f)
            lineTo(width * 0.85f, height * 0.55f)
            lineTo(width, height * 0.3f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFF94A3B8)
    )

    // Mid-ground rounded hills (Soft green-gray)
    drawPath(
        path = Path().apply {
            moveTo(0f, height * 0.88f)
            quadraticTo(width * 0.25f, height * 0.78f, width * 0.55f, height * 0.88f)
            quadraticTo(width * 0.8f, height * 0.95f, width, height * 0.82f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFF6B7280).copy(alpha = 0.4f)
    )

    // Blue body of water on the left (Light teal)
    drawPath(
        path = Path().apply {
            moveTo(0f, height * 0.88f)
            lineTo(width * 0.32f, height * 0.95f)
            lineTo(width * 0.25f, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFFA5F3FC)
    )

    // Main foreground grassy hill (Vibrant lime green)
    drawPath(
        path = Path().apply {
            moveTo(width * 0.25f, height)
            lineTo(width * 0.42f, height * 0.9f)
            quadraticTo(width * 0.65f, height * 0.8f, width * 0.92f, height * 0.88f)
            lineTo(width, height * 0.85f)
            lineTo(width, height)
            close()
        },
        color = Color(0xFFA3E635)
    )

    // Sharp rocky cliff edge under the deer
    drawPath(
        path = Path().apply {
            moveTo(width * 0.42f, height * 0.9f)
            lineTo(width * 0.38f, height * 0.96f)
            lineTo(width * 0.45f, height * 0.99f)
            lineTo(width * 0.6f, height * 0.88f)
            close()
        },
        color = Color(0xFF334155).copy(alpha = 0.4f)
    )

    // Tall Tree Trunk on the right edge
    drawRect(
        color = Color(0xFF451A03),
        topLeft = Offset(width - 75f, height * 0.15f),
        size = Size(40f, height * 0.85f)
    )
    
    // Lush Dark Green Canopy from top
    drawPath(
        path = Path().apply {
            moveTo(width, 0f)
            lineTo(width - 600f, 0f)
            quadraticTo(width - 550f, 180f, width - 350f, 220f)
            quadraticTo(width - 150f, 200f, width, 140f)
            close()
        },
        color = Color(0xFF064E3B)
    )
    
    // Very long thin vines hanging from canopy
    val vineCol = Color(0xFF064E3B)
    drawLine(vineCol, Offset(width - 380f, 205f), Offset(width - 400f, 380f), 3f)
    drawLine(vineCol, Offset(width - 240f, 195f), Offset(width - 230f, 480f), 4.5f)
    drawLine(vineCol, Offset(width - 120f, 160f), Offset(width - 130f, 320f), 3.5f)

    // Highly precise grazing deer
    drawGrazingDeer(Offset(width * 0.48f, height * 0.78f))
}

private fun DrawScope.drawGrazingDeer(position: Offset) {
    withTransform({
        translate(position.x, position.y)
    }) {
        // Rear legs
        drawLine(Color(0xFF451A03), Offset(85f, 45f), Offset(90f, 92f), 3.5f)
        drawLine(Color(0xFF451A03), Offset(72f, 48f), Offset(70f, 88f), 3f)

        // Body (Slanted back, tan color)
        drawPath(
            path = Path().apply {
                moveTo(15f, 15f)
                quadraticTo(55f, 2f, 95f, 18f)
                lineTo(102f, 48f)
                lineTo(25f, 52f)
                close()
            },
            color = Color(0xFFD97706)
        )
        
        // White spots and underbelly area
        drawCircle(Color.White.copy(alpha = 0.6f), 2.5f, Offset(55f, 18f))
        drawCircle(Color.White.copy(alpha = 0.6f), 2f, Offset(70f, 14f))
        drawCircle(Color.White.copy(alpha = 0.6f), 2f, Offset(82f, 28f))
        
        // Neck (Arching down)
        drawPath(
            path = Path().apply {
                moveTo(20f, 20f)
                quadraticTo(0f, 35f, -12f, 55f)
                lineTo(-2f, 65f)
                lineTo(30f, 35f)
                close()
            },
            color = Color(0xFFD97706)
        )
        
        // Head (Looking down)
        drawCircle(Color(0xFFD97706), 13f, Offset(-14f, 60f))
        
        // Antlers (Dark brown, detailed)
        val antlerColor = Color(0xFF451A03)
        drawLine(antlerColor, Offset(-2f, 42f), Offset(-20f, 18f), 2.5f)
        drawLine(antlerColor, Offset(-12f, 30f), Offset(-28f, 25f), 2f)
        drawLine(antlerColor, Offset(-8f, 35f), Offset(-15f, 5f), 2f)

        // Front legs
        drawLine(antlerColor, Offset(35f, 50f), Offset(32f, 95f), 3.5f)
        drawLine(antlerColor, Offset(48f, 52f), Offset(55f, 92f), 3f)
    }
}

private fun DrawScope.drawGrass(position: Offset) {
    val color = Color(0xFF14532D).copy(alpha = 0.6f)
    drawLine(color, position, Offset(position.x - 5f, position.y - 15f), 2f)
    drawLine(color, position, Offset(position.x, position.y - 20f), 2f)
    drawLine(color, position, Offset(position.x + 5f, position.y - 15f), 2f)
}

private fun DrawScope.drawRainy() {
    val width = size.width
    val height = size.height

    // Background sky (Dark blue gradient is handled by SeasonPage, but we can add depth)
    drawRect(
        color = Color(0xFF1E293B).copy(alpha = 0.5f),
        size = size
    )

    // Far background mountains (Darker blue silhouettes)
    drawMountain(
        path = Path().apply {
            moveTo(0f, height * 0.75f)
            lineTo(width * 0.25f, height * 0.45f)
            lineTo(width * 0.5f, height * 0.65f)
            lineTo(width * 0.75f, height * 0.4f)
            lineTo(width, height * 0.7f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFF0F172A)
    )

    // Mid-ground rocky cliffs
    drawPath(
        path = Path().apply {
            moveTo(width * 0.35f, height)
            lineTo(width * 0.55f, height * 0.65f)
            lineTo(width * 0.85f, height * 0.72f)
            lineTo(width, height * 0.6f)
            lineTo(width, height)
            close()
        },
        color = Color(0xFF1E293B)
    )

    // Water/mist at bottom left
    drawRect(
        color = Color(0xFF38BDF8).copy(alpha = 0.2f),
        topLeft = Offset(0f, height * 0.85f),
        size = Size(width * 0.4f, height * 0.15f)
    )

    // Tree Trunk (Right)
    drawRect(
        color = Color(0xFF020617),
        topLeft = Offset(width - 65f, height * 0.15f),
        size = Size(35f, height * 0.85f)
    )

    // Top Leaves/Canopy (Darker, almost black)
    drawPath(
        path = Path().apply {
            moveTo(width, 0f)
            lineTo(width - 500f, 0f)
            quadraticTo(width - 450f, 180f, width - 250f, 210f)
            quadraticTo(width - 80f, 180f, width, 140f)
            close()
        },
        color = Color(0xFF020617)
    )

    // Hanging vines
    val vineCol = Color(0xFF020617)
    drawLine(vineCol, Offset(width - 320f, 195f), Offset(width - 340f, 350f), 3f)
    drawLine(vineCol, Offset(width - 220f, 205f), Offset(width - 215f, 420f), 4f)

    // Wolf Silhouette (Howling)
    drawHowlingWolf(Offset(width * 0.48f, height * 0.65f))

    // Rain streaks (Diagonal falling rain)
    for (i in 0..60) {
        val x = (0..width.toInt()).random().toFloat()
        val y = (0..height.toInt()).random().toFloat()
        drawLine(
            color = Color.White.copy(alpha = 0.2f),
            start = Offset(x, y),
            end = Offset(x - 10f, y + 30f),
            strokeWidth = 1.5f
        )
    }

    // Glowing lights (Fireflies)
    drawCircle(Color(0xFFFDE047).copy(alpha = 0.6f), 4f, Offset(width * 0.62f, height * 0.45f))
    drawCircle(Color(0xFFFDE047).copy(alpha = 0.4f), 3f, Offset(width * 0.25f, height * 0.62f))
    drawCircle(Color(0xFFFDE047).copy(alpha = 0.3f), 5f, Offset(width * 0.88f, height * 0.55f))
}

private fun DrawScope.drawHowlingWolf(position: Offset) {
    withTransform({
        translate(position.x, position.y)
    }) {
        val wolfColor = Color(0xFF020617)
        
        // Tail
        drawPath(
            path = Path().apply {
                moveTo(70f, 50f)
                quadraticTo(100f, 90f, 110f, 60f)
                quadraticTo(100f, 40f, 70f, 45f)
                close()
            },
            color = wolfColor
        )

        // Body
        drawPath(
            path = Path().apply {
                moveTo(20f, 20f) // Chest
                quadraticTo(45f, 5f, 75f, 15f) // Back
                lineTo(80f, 55f) // Hind
                lineTo(25f, 60f) // Front belly
                close()
            },
            color = wolfColor
        )

        // Neck and Head (Howling)
        drawPath(
            path = Path().apply {
                moveTo(25f, 25f)
                quadraticTo(15f, 0f, -10f, -40f) // Muzzle top
                lineTo(-5f, -45f) // Tip of nose
                lineTo(5f, -38f) // Jaw
                quadraticTo(20f, -5f, 35f, 20f)
                close()
            },
            color = wolfColor
        )
        
        // Ear
        drawPath(
            path = Path().apply {
                moveTo(5f, -15f)
                lineTo(0f, -30f)
                lineTo(12f, -18f)
                close()
            },
            color = wolfColor
        )

        // Legs
        drawLine(wolfColor, Offset(35f, 58f), Offset(32f, 90f), 5f) // Front
        drawLine(wolfColor, Offset(75f, 55f), Offset(80f, 90f), 5f) // Back
    }
}

private fun DrawScope.drawAutumn() {
    val width = size.width
    val height = size.height

    // Far mountains (Pale)
    drawMountain(
        path = Path().apply {
            moveTo(0f, height * 0.6f)
            lineTo(width * 0.25f, height * 0.45f)
            lineTo(width * 0.5f, height * 0.55f)
            lineTo(width * 0.75f, height * 0.4f)
            lineTo(width, height * 0.6f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFFFED7AA).copy(alpha = 0.4f)
    )

    // Mid-ground cliff (Left)
    drawPath(
        path = Path().apply {
            moveTo(0f, height * 0.8f)
            lineTo(width * 0.4f, height * 0.65f)
            lineTo(width * 0.5f, height * 0.7f)
            lineTo(width * 0.45f, height * 0.85f)
            lineTo(width * 0.55f, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFF92400E)
    )

    // Tree (Right)
    val treeTrunkWidth = 40f
    drawRect(
        color = Color(0xFF78350F),
        topLeft = Offset(width - treeTrunkWidth - 60f, height * 0.2f),
        size = Size(treeTrunkWidth, height * 0.8f)
    )
    
    // Tree leaves/canopy (Top right)
    drawCircle(
        color = Color(0xFFEA580C),
        radius = 150f,
        center = Offset(width - 50f, height * 0.15f)
    )
    drawCircle(
        color = Color(0xFFF97316),
        radius = 100f,
        center = Offset(width - 150f, height * 0.1f)
    )

    // Squirrel on cliff
    drawSquirrel(Offset(width * 0.25f, height * 0.68f), isFlipped = true)

    // Squirrel on tree branch (Branch first)
    drawLine(
        color = Color(0xFF78350F),
        start = Offset(width - 100f, height * 0.45f),
        end = Offset(width - 250f, height * 0.4f),
        strokeWidth = 10f
    )
    drawSquirrel(Offset(width - 200f, height * 0.38f), isFlipped = false)

    // Falling leaves
    for (i in 0..15) {
        val x = (0..width.toInt()).random().toFloat()
        val y = (0..height.toInt()).random().toFloat()
        drawLeaf(Offset(x, y))
    }
}

private fun DrawScope.drawSquirrel(position: Offset, isFlipped: Boolean) {
    val scale = if (isFlipped) -1f else 1f
    
    withTransform({
        translate(position.x, position.y)
        scale(scale, 1f, Offset.Zero)
    }) {
        // Tail
        drawPath(
            path = Path().apply {
                moveTo(0f, 0f)
                cubicTo(-20f, -10f, -30f, -40f, -10f, -50f)
                cubicTo(5f, -55f, 20f, -40f, 10f, -10f)
                close()
            },
            color = Color(0xFFB45309)
        )
        // Body
        drawOval(
            color = Color(0xFFD97706),
            topLeft = Offset(0f, -15f),
            size = Size(30f, 20f)
        )
        // Head
        drawCircle(
            color = Color(0xFFD97706),
            radius = 8f,
            center = Offset(25f, -18f)
        )
        // Ear
        drawPath(
            path = Path().apply {
                moveTo(22f, -25f)
                lineTo(25f, -35f)
                lineTo(28f, -25f)
                close()
            },
            color = Color(0xFFD97706)
        )
    }
}

private fun DrawScope.drawLeaf(center: Offset) {
    drawPath(
        path = Path().apply {
            moveTo(center.x, center.y - 8f)
            quadraticTo(center.x + 8f, center.y, center.x, center.y + 8f)
            quadraticTo(center.x - 8f, center.y, center.x, center.y - 8f)
            close()
        },
        color = Color(0xFFF97316).copy(alpha = 0.6f)
    )
}

private fun DrawScope.drawWinter() {
    val width = size.width
    val height = size.height

    // 1. Far background mountains (Very light purple)
    drawPath(
        path = Path().apply {
            moveTo(0f, height * 0.7f)
            lineTo(width * 0.15f, height * 0.55f)
            lineTo(width * 0.35f, height * 0.65f)
            lineTo(width * 0.55f, height * 0.45f)
            lineTo(width * 0.75f, height * 0.6f)
            lineTo(width * 0.9f, height * 0.4f)
            lineTo(width, height * 0.5f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFFDDD6FE).copy(alpha = 0.5f)
    )

    // 2. Mid-ground mountains (Slightly darker)
    drawPath(
        path = Path().apply {
            moveTo(0f, height * 0.85f)
            lineTo(width * 0.25f, height * 0.65f)
            lineTo(width * 0.45f, height * 0.78f)
            lineTo(width * 0.7f, height * 0.62f)
            lineTo(width, height * 0.8f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        },
        color = Color(0xFFC4B5FD).copy(alpha = 0.4f)
    )

    // 3. The Cliff (Dark purple)
    drawPath(
        path = Path().apply {
            moveTo(width * 0.15f, height)
            lineTo(width * 0.4f, height * 0.65f) // Jagged peak
            lineTo(width * 0.45f, height * 0.7f)
            lineTo(width * 0.5f, height * 0.6f) // Another peak
            lineTo(width * 0.85f, height * 0.75f) // Platform for bear
            quadraticTo(width * 0.95f, height * 0.85f, width, height * 0.82f)
            lineTo(width, height)
            close()
        },
        color = Color(0xFF4C1D95) 
    )
    
    // Cliff side shadow/detail
    drawPath(
        path = Path().apply {
            moveTo(width * 0.15f, height)
            lineTo(width * 0.4f, height * 0.65f)
            lineTo(width * 0.45f, height * 0.9f)
            lineTo(width * 0.35f, height)
            close()
        },
        color = Color(0xFF2E1065)
    )

    // 4. Snow on the cliff platform
    drawPath(
        path = Path().apply {
            moveTo(width * 0.5f, height * 0.6f)
            lineTo(width * 0.85f, height * 0.75f)
            lineTo(width * 0.82f, height * 0.78f)
            lineTo(width * 0.52f, height * 0.65f)
            close()
        },
        color = Color.White.copy(alpha = 0.7f)
    )

    // 5. Tree on the right
    drawRect(
        color = Color(0xFF2E1065),
        topLeft = Offset(width * 0.88f, height * 0.15f),
        size = Size(25f, height * 0.85f)
    )
    
    // Canopy (Purple)
    drawPath(
        path = Path().apply {
            moveTo(width, 0f)
            lineTo(width * 0.5f, 0f)
            quadraticTo(width * 0.55f, height * 0.12f, width * 0.75f, height * 0.15f)
            quadraticTo(width * 0.9f, height * 0.13f, width, height * 0.1f)
            close()
        },
        color = Color(0xFFA78BFA) 
    )
    
    val treeAccentColor = Color(0xFF2E1065)
    drawLine(treeAccentColor, Offset(width * 0.78f, height * 0.15f), Offset(width * 0.76f, height * 0.35f), 4f)
    drawLine(treeAccentColor, Offset(width * 0.85f, height * 0.14f), Offset(width * 0.86f, height * 0.42f), 5f)
    drawLine(treeAccentColor, Offset(width * 0.93f, height * 0.12f), Offset(width * 0.91f, height * 0.3f), 3f)

    // 6. Polar Bear (Looking left, on the platform)
    drawPolarBear(Offset(width * 0.65f, height * 0.58f))

    // 7. Snowflakes
    for (i in 0..60) {
        drawCircle(
            color = Color.White.copy(alpha = 0.5f),
            radius = (1..3).random().toFloat(),
            center = Offset((0..width.toInt()).random().toFloat(), (0..height.toInt()).random().toFloat())
        )
    }
}

private fun DrawScope.drawPolarBear(position: Offset) {
    withTransform({
        translate(position.x, position.y)
        scale(1.2f, 1.2f, Offset.Zero)
    }) {
        val bearColor = Color.White
        val shadowColor = Color(0xFFEDE9FE)

        // Far legs (shadowed)
        drawPath(
            path = Path().apply {
                moveTo(60f, 50f)
                lineTo(65f, 85f)
                lineTo(75f, 85f)
                lineTo(75f, 55f)
                close()
            },
            color = shadowColor
        )
        drawPath(
            path = Path().apply {
                moveTo(20f, 55f)
                lineTo(15f, 82f)
                lineTo(25f, 82f)
                lineTo(30f, 55f)
                close()
            },
            color = shadowColor
        )

        // Body
        drawPath(
            path = Path().apply {
                moveTo(0f, 30f) // Chest
                quadraticTo(30f, 0f, 80f, 10f) // Back
                quadraticTo(100f, 25f, 95f, 65f) // Rear
                lineTo(20f, 70f) // Belly
                close()
            },
            color = bearColor
        )
        
        // Near legs
        drawPath(
            path = Path().apply {
                moveTo(20f, 55f)
                lineTo(15f, 90f)
                lineTo(30f, 90f)
                lineTo(40f, 55f)
                close()
            },
            color = bearColor
        )
        drawPath(
            path = Path().apply {
                moveTo(75f, 55f)
                lineTo(85f, 92f)
                lineTo(100f, 92f)
                lineTo(95f, 60f)
                close()
            },
            color = bearColor
        )

        // Neck and Head
        drawPath(
            path = Path().apply {
                moveTo(5f, 35f)
                quadraticTo(-15f, 30f, -35f, 40f) // Muzzle top
                lineTo(-38f, 50f) // Nose
                lineTo(-25f, 58f) // Jaw
                quadraticTo(-5f, 60f, 15f, 50f)
                close()
            },
            color = bearColor
        )
        
        // Ear
        drawCircle(bearColor, 4f, Offset(0f, 32f))
        // Eye
        drawCircle(Color.Black, 1.5f, Offset(-20f, 44f))
        // Nose
        drawCircle(Color.Black, 2.5f, Offset(-36f, 48f))
    }
}

private fun DrawScope.drawMountain(path: Path, color: Color) {
    drawPath(path = path, color = color)
}
