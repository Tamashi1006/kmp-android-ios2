import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Conversation(SampleData.conversationSample)
    }
}

data class Message(val author: String, val body: String)
@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        /*Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )*/

        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,)
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Lexi",
            """Creo que Kotlin es mi lenguaje de programación favorito.
            |¡Es muy divertido!""".trim()
        ),
        Message(
            "Lexi",
            "Buscando alternativas a los diseños XML..."
        ),
        Message(
            "Lexi",
            """Oye, echa un vistazo a Jetpack Compose, ¡es genial!
            |Es el conjunto de herramientas moderno de Android para crear una interfaz de usuario nativa.
            |Simplifica y acelera el desarrollo de UI en Android.
            |Menos código, herramientas potentes y API de Kotlin intuitivas :)""".trim()
        ),
        Message(
            "Lexi",
            "Está disponible desde API 21+ :)"
        ),
        Message(
            "Lexi",
            "Escribir Kotlin para UI parece tan natural. Compose, ¿dónde has estado toda mi vida?"
        ),
        Message(
            "Lexi",
            "El nombre de la próxima versión de Android Studio es Arctic Fox"
        ),
        Message(
            "Lexi",
            "Las herramientas de Android Studio Arctic Fox para Compose son de primera categoría ^_^"
        ),
        Message(
            "Lexi",
            "No sabía que ahora puedes ejecutar el emulador directamente desde Android Studio"
        ),
        Message(
            "Lexi",
            "Las vistas previas de composición son excelentes para comprobar rápidamente cómo se ve un diseño componible.\n"
        ),
        Message(
            "Lexi",
            "Las vistas previas también son interactivas después de habilitar la configuración experimental.\n"
        ),
        Message(
            "Lexi",
            "¿Has intentado escribir build.gradle con KTS?"
        ),
    )
}

